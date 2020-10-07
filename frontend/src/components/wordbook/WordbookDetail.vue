<template>
  <div class="wordbook-detail" align="center">
    <div v-if="wordList.length != 0">
      <v-progress-circular
        :rotate="-90"
        :size="50"
        :width="10"
        :value="progress"
        color="var(--main-color)"
        class="wordbook-progress"
        v-if="!chk"
      >
        {{ progress }}
      </v-progress-circular>
      <h2 class="eng-text" v-if="!chk">{{ wordbook.name }}</h2>
      <h2 class="eng-text" v-if="chk">Level {{ wordbook.level }}</h2>
      <div>
        <span class="wordList-lenght">단어({{ wordList.length }})</span>
      </div>
      <v-divider style="margin: 10px 0px 5px 0px"></v-divider>
      <div v-if="!chk">
        <div class="switch-container">
          <span class="quiz-container">
            <v-btn
              rounded
              color="var(--main-point-color)"
              dark
              @click="initQuiz"
              :wordList="wordList"
            >
              Quiz
            </v-btn>
          </span>
          <WordCreate :wordbook_id="wordbook.id" :isBase="isBase"/>
          
          <span :class="isShowEng ? switchWord[1] : switchWord[0]"> 단어</span>
          <v-switch
            class="switch-word"
            color="var(--main-color)"
            v-model="isShowEng"
          >
          </v-switch>

          <span :class="isShowKor ? switchWord[1] : switchWord[0]"> 뜻</span>
          <v-switch
            class="switch-word"
            color="var(--main-color)"
            v-model="isShowKor"
          >
          </v-switch>
        </div>
      </div>
      <div class="wordList-container">
        <Word
          :word="word"
          :isShowEng="isShowEng"
          :isShowKor="isShowKor"
          v-for="word in wordList"
          :key="word.id"
        />
      </div>

      <!-- <p @click="back">뒤로가기</p> -->
      <div v-if="false" class="wordbookFAB">
        <WordbookFAB />
      </div>
      <div v-if="isSelectedQuizOption">
        <WordQuizOption :wordList="wordList" />
      </div>
    </div>
    <div v-if="wordList.length==0">
      <img
        class="no-word"
        :src="require(`@/assets/noWords.png`)"
      />
      <div />
      <WordCreate :wordbook_id="wordbook.id" :isBase="isBase"/>
      <h3 class="mt-3"> 추가하기 </h3>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import Word from "@/components/wordbook/word/Word.vue";
import WordbookFAB from "@/components/wordbook/WordbookFAB.vue";
import WordQuizOption from "@/components/wordbook/quiz/WordQuizOption.vue";
import WordCreate from "@/components/wordbook/word/WordCreate.vue";


export default {
  name: "WordbookDetail",
  data() {
    return {
      wordList: [],
      quizDialog: false,
      selectAll: false,
      isShowKor: true,
      isShowEng: true,
      switchWord: ["switch-kor-before", "switch-kor-after"],
      progress: 0,
    };
  },
  props: {
    isBase: {
      type: Boolean,
      default: true
    }
  },
  computed: {
    ...mapGetters(["wordbook", "isSelectedQuizOption"]),

    chk() {
      return this.isBase
    }
  },
  methods: {
    ...mapMutations(["INIT_QUIZ", "SET_WORDBOOK"]),
    back() {
      this.$router.push({ name: "WordbookList" });
    },
    initQuiz() {
      this.INIT_QUIZ();
    },
  },
  components: {
    Word,
    WordbookFAB,
    WordQuizOption,
    WordCreate,
  },
  created() {
    let count = 0;
    this.wordList = this.wordbook.words;
    this.wordList.forEach((word) => {
      if (word.check) {
        count++;
      }
    });

    this.interval = setInterval(() => {
      return (this.progress = Math.round((count / this.wordList.length) * 100));
    }, 1000);
  },
  watch: {
    wordbook(val) {
      console.log(val);
      this.wordList = val.words;
    },
  },
};
</script>

<style>
.wordbookFAB {
  float: right;
  float: bottom;
  margin-right: 60px;
  margin-top: auto;
}
.switch-word {
  display: inline-block;
}
.switch-kor-before {
  color: var(--font-soft-color);
}
.switch-kor-after {
  color: var(--main-color);
}

.switch-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.quiz-container {
  margin-right: auto;
}
.wordList-lenght {
  color: var(--font-soft-color);
}

.wordList-container {
  /* height: 370px;
  overflow-y: auto; */
  margin-bottom: 56px;
}
.wordbook-progress {
  margin: 10px 0px;
}
.delete-button {
  margin: 8px;
}
.no-word {
  width: 80%;
  height: 80%;
  margin-top: 30%;
  margin-bottom: 10%;
}
</style>