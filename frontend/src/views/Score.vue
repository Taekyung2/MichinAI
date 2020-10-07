<template>
  <div class="container">
    <img
      v-if="chatChartData.labels"
      class="no-score-img"
      :src="require(`@/assets/noScore.png`)"
    />
    <div v-else>
      <div align="right">
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-icon color="#7db3d9" dark v-bind="attrs" v-on="on">
              mdi-information-outline
            </v-icon>
          </template>
          <span
            >내가 보낸 채팅이 10개 이상의 경우<br />
            점수 확인이 가능합니다!</span
          >
        </v-tooltip>
      </div>
      <div class="score-container">
        <LineChart
          :chart-data="chatChartData"
          @on-receive="changeSelect"
          class="chart"
        />
      </div>
      <h3>감점 요소</h3>
      <h4>{{ selectDate }}</h4>
      <div class="score-container">
        <v-alert
          v-if="selectIdx == -1"
          class="text-center"
          outlined
          color="#f48705"
        >
          점수 차트의 <strong color="#f48705">포인터</strong>를 클릭해<br />
          감점요소를 파악해보세요 :)
        </v-alert>
        <PieChart v-else :chart-data="pieChartData" class="chart" />
      </div>
    </div>
  </div>
</template>

<script>
import LineChart from "@/components/score/LineChart.vue";
import PieChart from "@/components/score/PieChart.vue";
import SERVER from "@/api/spring";
import { mapMutations } from "vuex";

import axios from "axios";
export default {
  name: "Score",
  components: {
    LineChart,
    PieChart,
  },
  data: () => ({
    chatChartData: {},
    chatCateCnt: [],
    pieChartData: {},
    selectIdx: -1,
    selectDate: "",
  }),
  mounted() {
    this.init();
  },
  created() {
    this.SET_NAVIGATION_TITLE("채팅 점수");
  },
  methods: {
    ...mapMutations(["SET_NAVIGATION_TITLE"]),
    init() {
      var moment = require("moment");
      const userBotKey = this.$store.state.Kakao.account.userBotKey;
      axios
        .get(SERVER.URL + "/chat/score?userBotKey=" + userBotKey)
        .then((res) => {
          var labels = [],
            score = [],
            cateCnt = [];
          res.data.chatList.forEach((elem) => {
            if (elem.score.score != -1) {
              labels.push(moment(elem.date).format("MM-DD"));
              score.push(elem.score.score);
              cateCnt.push(elem.score.categoryCnt);
            }
          });
          this.chatChartData = {
            labels: labels,
            data: score,
          };
          this.chatCateCnt = cateCnt;
        });
    },
    changeSelect(item) {
      this.selectIdx = item.idx;
      let labels = [],
        data = [];
      Object.entries(this.chatCateCnt[this.selectIdx]).forEach((arr) => {
        labels.push(arr[0]);
        data.push(arr[1]);
      });
      this.pieChartData = {
        labels: labels,
        data: data,
      };
      this.selectDate = this.chatChartData.labels[this.selectIdx];
    },
  },
};
</script>

<style>
.score-container {
  margin: 20px 0;
}
.chart {
  height: 180px;
}
.no-score-img {
  display: block;
  width: 70%;
  margin: 80px auto;
}
</style>