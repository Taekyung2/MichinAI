<template>
  <div class="container">
    <div class="score-container">
      <LineChart
        :chart-data="chatChartData"
        @on-receive="changeSelect"
        class="chart"
      />
    </div>
    <h3>감점 요소</h3>
    <h4>{{selectDate}}</h4>
    <div class="score-container">
      <PieChart :chart-data="pieChartData" class="chart" />
    </div>
  </div>
</template>

<script>
import LineChart from "@/components/score/LineChart.vue";
import PieChart from "@/components/score/PieChart.vue";
import SERVER from '@/api/spring';

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
    selectDate:""
  }),
  mounted() {
    this.init();
  },
  methods: {
    init() {
      const userBotKey = this.$store.state.Kakao.account.userBotKey;
      axios
        .get(SERVER.URL + "/chat/score?userBotKey=" + userBotKey)
        .then((res) => {
          var labels = [],
            score = [],
            cateCnt = [];
          res.data.chatList.forEach((elem) => {
            labels.push(elem.date);
            score.push(elem.score.score);
            cateCnt.push(elem.score.categoryCnt);
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
      let labels = [], data = []
      Object.entries(this.chatCateCnt[this.selectIdx]).forEach((arr)=>{
        labels.push(arr[0])
        data.push(arr[1])
      });
      this.pieChartData = {
        labels : labels,
        data : data
      }
      this.selectDate = this.chatChartData.labels[this.selectIdx]
    },
  },
};
</script>

<style>
.score-container {
  margin: 50px 0;
}
.chart {
  height: 200px;
}
</style>