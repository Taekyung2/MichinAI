<template>
  <div class="choice-container my-5">
    <button
      v-for="(choiceKor, index) in choiceKorList"
      :key="index"
      class="choice-btn"
      @click="submitUserAnswer(choiceKor)"
      v-text="choiceKor"
      :disabled="isClickWord"
    ></button>
  </div>
</template>

<script>
export default {
  name: "WordQuizTypeChoiceKor",
  props: {
    choiceKorList: Array,
    quizAnswer: Object,
  },
  data() {
    return {
      isClickWord: false,
    };
  },
  methods: {
    submitUserAnswer(choiceKor) {
      event.target.classList.remove("v-btn--disabled");
      event.target.classList.remove("success--text");
      this.isClickWord = true;
      if (this.quizAnswer.kor == choiceKor) {
        this.$emit("next-choice-quiz", "O");
        event.target.classList.add("answer-word");
      } else {
        this.$emit("next-choice-quiz", "X");
        event.target.classList.add("wrong-word");
        for (let i = 0; i < this.choiceKorList.length; i++) {
          const korEl = document.getElementsByClassName("choice-btn")[i];
          if (korEl.textContent == this.quizAnswer.kor)
            korEl.classList.add("answer-word");
        }
      }
      const eventClass = event.target.classList;
      setTimeout(
        function () {
          eventClass.remove("wrong-word");
          console.log(document.getElementsByClassName("answer-word")[0]);
          document
            .getElementsByClassName("answer-word")[0]
            .classList.remove("answer-word");
          this.isClickWord = false;
        }.bind(this),
        1000
      );
    },
  },
};
</script>

<style>
.choice-container {
  display: flex;
  flex-direction: column;

  align-items: center;
  height: 250px;
  justify-content: space-between;
}
.choice-btn {
  width: 85%;
  margin: 5px 0;
  word-break: keep-all;
  white-space: pre-wrap;
  flex: 1 1 auto;
  border-radius: 5px;
  border: 1px solid var(--border-base-color);
  padding: 10px;
  text-transform: uppercase;
  transition-duration: 0.28s;
  transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
}
.answer-word {
  background: #43A047 !important;
  color: white;
}
.wrong-word {
  background: #f4511e !important;
  color: white;
}
/* button:disabled {
  opacity: 0.6;
  background: tomato;
} */
.disabled {
  pointer-events: none;
}
</style>