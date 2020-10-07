<template>
  <v-dialog :value="isSelectedQuizOption" max-width="500px" persistent>
    <v-card>
      <v-toolbar dark color="#f2cc06">
        <v-btn icon dark @click="SET_SELECTED_QUIZ_OPTION">
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>퀴즈 설정</v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>

      <div>
        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              <h5 class="option-title">퀴즈유형</h5>
            </v-list-item-title>
            <v-list-item-subtitle>
              <v-chip-group
                v-model="quizOption.selectedType"
                column
                mandatory
                :active-class="selectedTag"
              >
                <v-chip filter outlined value="OX"> OX </v-chip>
                <v-chip
                  filter
                  outlined
                  value="객관식"
                  :disabled="wordList.length < 10 ? true : false"
                >
                  객관식
                </v-chip>
              </v-chip-group>
              <!-- <span class="text-caption" v-if="wordList.length < 10"
                >객관식은 단어가 10개 이상부터 가능해요!</span
              > -->
              <p
                v-if="quizOption.selectedType == 'OX'"
                class="quiz-type-explanation"
              >
                단어를 보고 뜻을 '아는지', '모르는지' 체크하는 간단한 단어
                암기법
              </p>
              <p
                v-if="quizOption.selectedType == '객관식'"
                class="quiz-type-explanation"
              >
                4지 선다형으로 단어와 알맞은 뜻을 고르는 단어 퀴즈
              </p>
            </v-list-item-subtitle>
            <v-divider></v-divider>
          </v-list-item-content>
        </v-list-item>

        <!-- <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>
                        <h5 class="option-title">제한시간</h5>
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        <v-select
                        v-model="quizOption.selectedTime"
                        :items="quizTimeList"
                        menu-props="auto"
                        label="제한시간"
                        hide-details
                        prepend-icon="mdi-alarm"
                        single-line
                        class="select-time"
                        ></v-select>
                    </v-list-item-subtitle>
                    <v-divider></v-divider>
                </v-list-item-content>
            </v-list-item> -->

        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              <h5 class="option-title">순서</h5>
            </v-list-item-title>
            <v-list-item-subtitle>
              <v-chip-group
                v-model="quizOption.selectedSequence"
                column
                mandatory
                :active-class="selectedTag"
              >
                <v-chip
                  filter
                  outlined
                  v-for="(quizSequence, index) in quizSequenceList"
                  :key="index"
                  :value="quizSequence"
                >
                  {{ quizSequence }}
                </v-chip>
              </v-chip-group>
            </v-list-item-subtitle>
            <v-divider></v-divider>
          </v-list-item-content>
        </v-list-item>

        <v-list-item>
          <v-list-item-content>
            <v-list-item-title>
              <h5 class="option-title">문항수</h5>
            </v-list-item-title>
            <v-list-item-subtitle>
              <v-chip-group
                v-model="quizOption.selectedQuestionNumber.type"
                column
                mandatory
                :active-class="selectedTag"
              >
                <v-chip
                  filter
                  outlined
                  v-for="(quizCount, index) in quizQuestionNumberList"
                  :key="index"
                  :value="quizCount"
                >
                  {{ quizCount }}
                </v-chip>
              </v-chip-group>
            </v-list-item-subtitle>
            <div
              v-if="
                quizOption.selectedQuestionNumber.type ==
                quizQuestionNumberList[2]
              "
            >
              <v-slider
                v-model="quizOption.selectedQuestionNumber.number"
                thumb-label
                ticks
                :max="wordList.length"
                :min="quizOption.selectedType == 'OX' ? 1 : 10"
                :messages="
                  quizOption.selectedQuestionNumber.number +
                  ' / ' +
                  wordList.length
                "
              >
                <template v-slot:prepend>
                  <v-icon
                    class="count-change-btn"
                    @click="countDecrement"
                    color="var(--main-color)"
                  >
                    mdi-minus
                  </v-icon>
                </template>

                <template v-slot:append>
                  <v-icon
                    class="count-change-btn"
                    @click="countIncrement"
                    color="var(--main-color)"
                  >
                    mdi-plus
                  </v-icon>
                </template>
              </v-slider>
            </div>
            <v-divider></v-divider>
          </v-list-item-content>
        </v-list-item>
        <v-list-item class="option-start">
          <v-list-item-content>
            <v-btn color="#7db3d9" dark @click="openQuiz"> START </v-btn>
          </v-list-item-content>
        </v-list-item>
      </div>

      <div v-if="isStartedQuiz">
        <WordQuiz :wordList="wordList" :quizOption="quizOption" />
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from "vuex";
import WordQuiz from "@/components/wordbook/quiz/WordQuiz.vue";
export default {
  name: "WordQuizOption",
  data() {
    return {
      quizOption: {
        selectedType: "",
        selectedTime: "제한없음",
        selectedSequence: "",
        selectedQuestionNumber: {
          type: "",
          number: 1,
        },
      },
      quizTypeList: ["OX", "객관식"],
      quizTimeList: ["제한없음", "3초", "5초", "10초", "20초", "30초"],
      quizQuestionNumberList: ["전체", "미암기 단어", "사용자 정의"],
      quizSequenceList: ["순서대로", "랜덤"],
      selectedTag: "selected-tag",
    };
  },
  computed: {
    ...mapGetters(["isSelectedQuizOption", "isStartedQuiz"]),
  },
  props: {
    wordList: Array,
  },
  components: {
    WordQuiz,
  },
  methods: {
    ...mapMutations(["SET_SELECTED_QUIZ_OPTION", "SET_STARTED_QUIZ"]),
    openQuiz() {
      this.SET_STARTED_QUIZ();
    },
    countDecrement() {
      this.selectedQuestionNumber.number--;
    },
    countIncrement() {
      this.selectedQuestionNumber.numer++;
    },
  },
};
</script>

<style scoped>
.option-title {
  color: var(--font-sub-color);
}
.selected-tag {
  color: var(--main-point-color) !important;
}
.select-time {
  padding-top: 0px;
  margin-top: 8px;
  margin-bottom: 12px;
}
.v-messages__message {
  color: var(--font-sub-color);
  float: right;
  font-size: 14px;
  margin-bottom: 10px;
  margin-right: 5px;
}
.count-change-btn {
  margin: 0 5px;
}
.quiz-type-explanation {
  white-space: pre-line;
  font-size: 12px;
  color: var(--font-sub-color);
  padding: 5px;
}
.v-list-item__content {
  padding: 10px 0px;
  padding-bottom: 0px;
}
.option-start {
  padding: 10px;
  padding-bottom: 15px;
}
</style>