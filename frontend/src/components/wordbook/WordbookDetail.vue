<template>
  <div class="wordbook-detail" align="center">
      <h2>{{ wordbook.name }}</h2> 
      <span class="wordList-lenght">단어({{ wordList.length }})</span>
      <div>
        <div class="switch-container">
          <span class="quiz-container">
            <v-btn 
              rounded
              color="var(--main-point-color)" 
              dark
              @click="SET_SELECTED_QUIZ_OPTION"
              :wordList="wordList"
            > 
              Quiz
            </v-btn>
          </span>
          <span :class="isShowEng?switchWord[1]:switchWord[0]"> 단어</span>
          <v-switch 
            class="switch-word"
            color="var(--main-color)" 
            v-model="isShowEng">
          </v-switch>

          <span :class="isShowKor?switchWord[1]:switchWord[0]"> 뜻</span>
          <v-switch 
            class="switch-word"
            color="var(--main-color)" 
            v-model="isShowKor">
          </v-switch>
        </div>
      </div>
      <Word 
        :word="word"
        :isShowEng="isShowEng"
        :isShowKor="isShowKor"
        v-for="word in wordList" :key="word.id"/>
          
      <p @click="back">뒤로가기</p>
      <div class="wordbookFAB">
        <WordbookFAB/>
      </div>
      <div v-if="isSelectedQuizOption">
        <WordQuizOption :wordList="wordList"/>
      </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import Word from '@/components/wordbook/word/Word.vue'
import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'
import WordQuizOption from '@/components/wordbook/quiz/WordQuizOption.vue'

export default {
    name: 'WordbookDetail',
    data() {
      return {
        wordList : [
            {id:'1', eng: 'culture', kor: '문화, 교양'},
            {id:'2', eng: 'experience', kor: '경험'},
            {id:'3', eng: 'education', kor: '교육'},
            {id:'4', eng: 'symbol', kor: '상징'},
            {id:'5', eng: 'effect', kor: '결과, 영향, 효과'},
            {id:'6', eng: 'liberty', kor: '자유'},
            {id:'7', eng: 'affair', kor: '사건, 일'},
            {id:'8', eng: 'comfort', kor: '안락, 위안'},
        ],
        quizDialog: false,
        selectAll: false,
        isShowKor: true,
        isShowEng: true,
        switchWord: ['switch-kor-before', 'switch-kor-after' ]
      }
    },

    computed: {
      ...mapGetters([
        'wordbook',
        'isSelectedQuizOption'
      ]),

    },
    methods: {
      ...mapMutations([
        'SET_SELECTED_QUIZ_OPTION',
      ]),
      back(){
        this.$router.push({name:'WordbookList'})
      }
    },
    components: {
      Word,
      WordbookFAB,
      WordQuizOption
    }
}
</script>

<style>
.wordbookFAB{
  float: right;
  float: bottom;
  margin-right: 60px;
  margin-top: auto;
}
.switch-word{
  display: inline-block;
}
.switch-kor-before{
color: var(--font-soft-color);
}
.switch-kor-after{
color: var(--main-color);
}

.switch-container{
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
.quiz-container{
  margin-right: auto;
}
.wordList-lenght{
  color: var(--font-soft-color);
}

</style>