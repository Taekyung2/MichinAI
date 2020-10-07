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
          :border="0"
        >
          <slide
            :style="{ 'background-color': colorlist[i % 5] }"
            v-for="(wordbook, i) in wordbooklist"
            :index="i"
            :key="i"
          >
            <h2
              class="h2-font"
              align="center"
              style="
                 {
                  color: black;
                  margin-top: 35%;
                  margin-bottom: 35%;
                }
              "
            >
              {{ wordbook.name }}
            </h2>
            <img
              class="logo-image"
              :src="require(`@/assets/michinLogo03.svg`)"
            />
            <span class="delete-button" @click.stop="del(i)">
              <v-btn
                class="mx-3"
                fab
                dark
                x-small
                color="red"
              >
                <v-icon> mdi-close </v-icon>
              </v-btn>
            </span>
          </slide>
        </carousel-3d>
      </v-row>

      <div
        v-if="selectedMenuItem == 0 && wordbooklist.length == 0"
        style="margin-bottom: 20%"
      >
        <img
          class="mt-16"
          :src="require(`@/assets/noWordBooks.png`)"
          style="width: 60%"
        />
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
          :border="0"
        >
          <slide
            :style="{ 'background-color': colorlist[(i + 1) % 5] }"
            v-for="(wordbook, i) in getbaseWordbook"
            :index="i"
            :key="i"
          >
            <h2
              align="center"
              style="
                 {
                  color: black;
                  margin-top: 35%;
                  margin-bottom: 35%;
                }
              "
            >
              Level {{ wordbook.level }}
            </h2>
            <img
              class="logo-image"
              :src="require(`@/assets/michinLogo02.png`)"
            />
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
import { mapGetters, mapActions, mapMutations } from "vuex";
import { Carousel3d, Slide } from "vue-carousel-3d";
import axios from "axios";
import SERVER from "@/api/spring.js";
import WordbookCreate from "@/components/wordbook/WordbookCreate.vue";
// import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'

export default {
  name: "WordbookList",
  data() {
    return {
      selectedMenuItem: 0,
      colorlist: ["#E3F6CE", "#c6e2ff", "#ffd0d1", "#eeab73", "#4169e1"],
      wordbooklist: [],
      curIndex: 0,
      payload: {},
    };
  },
  components: {
    Carousel3d,
    Slide,
    WordbookCreate,
    // WordbookFAB
  },
  methods: {
    ...mapActions(["selectedWordbook"]),
    ...mapMutations(["SET_WORDBOOK"]),

    del(index) {
      axios.delete(SERVER.URL + "/wordbook/wordbook/" + this.wordbooklist[index].id + "/" + this.wordbooklist[index].user_id).then((res) => {
        this.SET_WORDBOOK(res);
      });
    },
    reset() {
      this.wordbooklist = this.getmyWordbook;
    },
    wordbookDetail() {
      this.payload = { wordbook: this.wordbooklist[this.curIndex], chk: false };
      this.selectedWordbook(this.payload);
    },
    basewordbookDetail() {
      this.payload = {
        wordbook: this.getbaseWordbook[this.curIndex],
        chk: true,
      };
      this.selectedWordbook(this.payload);
      console.log(this.getbaseWordbook[this.curIndex].id);
    },
    onAfterSlideChange(index) {
      this.curIndex = index;
    },
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

.theme--light.v-list {
  background: transparent;
}
.logo-image {
  width: 40%;
  margin-top: 10%;
  margin-left: 30%;
  margin-right: 30%;
}
.delete-button {
  float:right;
  margin : 0;
  padding : 0;
}
</style>