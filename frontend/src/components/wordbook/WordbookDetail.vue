<template>
  <div class="wordbook-detail">
      <h3>{{ wordbook.name }} 단어장 Detail 입니다.</h3>
      <div align="right">
      <v-btn 
        rounded
        color="var(--main-point-color)" 
        dark
        @click="quizDialog = true"
      >
        Quiz
      </v-btn>
      <div>
      <span :class="isShowKor?switchKor[1]:switchKor[0]"> 뜻보기</span>
       <v-switch 
       class="switch-kor"
        color="var(--main-color)" 
        v-model="isShowKor"></v-switch>
      </div>
      </div>
      <Word 
        :word="word"
        :isShowKor="isShowKor"
        v-for="word in wordList" :key="word.id"/>
          
      <p @click="back">뒤로가기</p>
      <div class="wordbookFAB">
        <WordbookFAB/>
      </div>

        <v-dialog
        v-model="quizDialog"
        max-width="500px"
        >
        <v-card>
            <v-card-title>
            퀴즈 선택
            </v-card-title>
            <v-card-text>
            <v-btn
                color="primary"
                dark
                @click="openQuizAll"
            >
                ALL
            </v-btn>
            <div v-if="quizAll">
              <WordQuizAll/>
            </div>

            <v-btn
                color="primary"
                dark
                @click="quizDialog = !quizDialog"
            >
                Random
            </v-btn>
          
            </v-card-text>
           
        </v-card>
        </v-dialog>
  </div>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import Word from '@/components/wordbook/Word.vue'
import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'
import WordQuizAll from '@/components/wordbook/WordQuizAll.vue'
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
        selectRandom: false,
        isShowKor: true,
        switchKor: ['switch-kor-before', 'switch-kor-after' ]
      }
    },

    computed: {
      ...mapGetters([
        'wordbook',
        'quizAll'
      ]),

    },
    methods: {
      ...mapMutations([
        'SET_SELECTED_QUIZ_ALL',
      ]),
      back(){
        this.$router.push({name:'WordbookList'})
      },
      openQuizAll(){
        this.quizDialog = !this.quizDialog
        this.SET_SELECTED_QUIZ_ALL()
        console.log(this.quizAll)
      }
    },
    components: {
      Word,
      WordbookFAB,
      WordQuizAll
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
.switch-kor{
  display: inline-block;
}
.switch-kor-before{
color: var(--font-soft-color);
}
.switch-kor-after{
color: var(--main-color);
}
</style>