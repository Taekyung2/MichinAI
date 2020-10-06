<template>
  <div class="container">
    <div class="wordbook-list-container" align="center">
      <v-list>
        <v-list-item-group
          v-model="selectedMenuItem"
          mandatory
          color="var(--main-point-color)"
          class="wordbook-menu-group"
        >
          <v-list-item class="menu-l wordbook-menu-item rounded-l-xl">
            <span>내 단어장</span>
          </v-list-item>
          <v-list-item class="menu-r wordbook-menu-item rounded-r-xl">
            <span>기본 단어장</span>
          </v-list-item>
        </v-list-item-group>
      </v-list>

      <!-- 내 단어장 -->
      <v-row
        v-if="selectedMenuItem == 0 && wordbooklist.length != 0"
        key="userWordbook"
        style="margin-bottom: 10%"
      >
        <carousel-3d
          @after-slide-change="onAfterSlideChange"
          :autoplay="true"
          :autoplay-timeout="3000"
          :count="wordbooklist.length"
          :width="170"
          :height="300"
          :space="150"
          :display="5"
          :controlsVisible="true"
          :onMainSlideClick="wordbookDetail"
          :autoplayHoverPause="true"
        >
          <slide
            :style="{ 'background-color': colorlist[i % 5] }"
            v-for="(wordbook, i) in wordbooklist"
            :index="i"
            :key="i"
          >
          <img
          class="incoming_msg_img"
          :src="require(`@/assets/michinLogo02.png`)"
          style="margin-bottom:15%"
          />
          <h2 align=center style="color:black">{{wordbook.name}}</h2>
          </slide>
        </carousel-3d>
      </v-row>

      <div
        v-if="selectedMenuItem == 0 && wordbooklist.length == 0"
        style="margin-bottom: 20%"
      >
        <img
          class="incoming_msg_img"
          :src="require(`@/assets/michinLogo04.png`)"
          style="width: 60%"
        />
        <h1>단어장을 추가해주세요</h1>
        <v-spacer></v-spacer>
      </div>

      <!-- 기본 단어장 -->
      <div v-if="selectedMenuItem == 1" key="baseWordbook">
        <carousel-3d
          @after-slide-change="onAfterSlideChange"
          :autoplay="true"
          :autoplay-timeout="3000"
          :width="170"
          :height="300"
          :space="150"
          :display="5"
          :controlsVisible="true"
          :onMainSlideClick="basewordbookDetail"
          :autoplayHoverPause="true"
        >
          <slide
            :style="{ 'background-color': colorlist[(i + 1) % 5] }"
            v-for="(wordbook, i) in getbaseWordbook"
            :index="i"
            :key="i"
          >
          <img
          class="incoming_msg_img"
          :src="require(`@/assets/michinLogo04.png`)"
          style="margin-bottom:15%"
          />
          <h2 align=center style="color:black">Level {{wordbook.level}}</h2>
          </slide>
        </carousel-3d>
      </div>
    </div>
    <div>
      <!-- <WordbookFAB/> -->
      <v-spacer></v-spacer>
      <WordbookCreate
        v-show="selectedMenuItem == 0"
        style="margin: 0 auto"
        @close="reset"
      />
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
import { Carousel3d, Slide } from "vue-carousel-3d";

import WordbookCreate from "@/components/wordbook/WordbookCreate.vue";
// import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'

export default {
  name: "WordbookList",
  data() {
    return {
      selectedMenuItem: 0,
      colorlist: ["#008080", "#c6e2ff", "#ffd0d1", "#eeab73", "#4169e1"],
      wordbooklist: [],
      curIndex: 0,
    };
  },
  components: {
    Carousel3d,
    Slide,
    WordbookCreate,
    // WordbookFAB
  },
  methods: {
    ...mapActions(['selectedWordbook']),

    reset() {
      this.wordbooklist = this.getmyWordbook;
    },
    wordbookDetail() {
      this.selectedWordbook(this.wordbooklist[this.curIndex])
    },
    basewordbookDetail() {
      this.selectedWordbook(this.getbaseWordbook[this.curIndex])
    },
    onAfterSlideChange(index) {
      this.curIndex = index;
    }
  },
  created() {
    this.reset();
  },
  computed: {
    ...mapGetters(["getmyWordbook", "getbaseWordbook"]),
  },

  watch: {
    getmyWordbook() {
      this.reset();
    },
  },
};
</script>

<style scoped>
.wordbook-menu-group {
  display: flex;
  border: 1px solid var(--border-base-color);
  border-radius: 24px !important;
  width: 230px;
  height: 48px;
  margin-bottom: 10px;
}
.wordbook-menu-item {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0 0;
}

.menu-l.v-list-item--link:before {
  border-top-left-radius: 24px !important;
  border-bottom-left-radius: 24px !important;
}

.menu-r.v-list-item--link:before {
  border-top-right-radius: 24px !important;
  border-bottom-right-radius: 24px !important;
}

.selected_menu-container {
  display: flex;
}
.carousel-3d-slider {
  width: 100px;
  height: 100px;
}
</style>