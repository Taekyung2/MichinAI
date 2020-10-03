<template>
  <div class="wordbook-detail" align="center">
     
      <v-progress-circular
        :rotate="-90"
        :size="60"
        :width="10"
        :value="progress"
        color="var(--main-color)"
        class="wordbook-progress"
      >
        {{ progress }}
      </v-progress-circular>
      <h2 class="eng-text">{{ wordbook.name }}</h2> 
      <span class="wordList-lenght">단어({{ wordList.length }})</span>
      <v-divider style="margin:10px 0px 5px 0px"></v-divider>
      <div>
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
      <div class="wordList-container">
        <Word 
          :word="word"
          :isShowEng="isShowEng"
          :isShowKor="isShowKor"
          v-for="word in wordList" :key="word.id"/>
      </div>
      <!-- <p @click="back">뒤로가기</p> -->
      <div v-if="false" class="wordbookFAB">
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
            {id:'1', eng: 'communicate ', kor: '연락을 주고받다, 의사소통을 하다, (정보 등을) 전달하다.', check: false},
            {id:'2', eng: 'experience', kor: '경험', check: true},
            {id:'3', eng: 'education', kor: '교육', check: true},
            {id:'4', eng: 'symbol', kor: '상징', check: true},
            {id:'5', eng: 'effect', kor: '결과, 영향, 효과', check: false},
            {id:'6', eng: 'liberty', kor: '자유', check: true},
            {id:'7', eng: 'affair', kor: '사건, 일', check: false},
            {id:'8', eng: 'comfort', kor: '안락, 위안', check: true},
            {id:'9', eng: 'progress', kor: '진전, 진척, 진행', check: false},
            {id:'10', eng: 'inadequate', kor: '불충분한, 부적당한', check: false},
            {id:'11', eng: 'test', kor: '테스트, 시도', check: false},
   
        ],
        quizDialog: false,
        selectAll: false,
        isShowKor: true,
        isShowEng: true,
        switchWord: ['switch-kor-before', 'switch-kor-after' ],
        progress: 0,
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
        'INIT_QUIZ',
      ]),
      back(){
        this.$router.push({name:'WordbookList'})
      },
      initQuiz(){
        this.INIT_QUIZ()
      }
    },
    components: {       
      Word,
      WordbookFAB,
      WordQuizOption
    },
    created () {
      let count=0
      this.wordList.forEach(word => {
         if(word.check) {
              count++
        }
      });
        
      this.interval = setInterval(() => {
        return this.progress = Math.round((count/this.wordList.length)*100)
      }, 1000)
    },
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

.wordList-container{
  /* height: 370px;
  overflow-y: auto; */
  margin-bottom: 56px;
}
.wordbook-progress{
  margin: 10px 0px;
}
</style>