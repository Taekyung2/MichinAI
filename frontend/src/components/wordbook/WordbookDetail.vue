<template>
  <div class="wordbook-detail">
      <h3>{{ wordbook.id }}번 단어장 Detail 입니다.</h3>
      <Word 
        :word="word"
        v-for="word in wordList" :key="word.id"/>
          
      <!-- <WordCreate/> -->
      <p @click="back">뒤로가기</p>
      <div class="wordbookFAB">
        <WordbookFAB/>
      </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Word from '@/components/wordbook/Word.vue'
// import WordCreate from '@/components/wordbook/WordCreate.vue'
import WordbookFAB from '@/components/wordbook/WordbookFAB.vue'

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
      }
    },
    computed: {
      ...mapGetters([
        'wordbook',
      ])
    },
    methods: {
      back(){
        this.$router.push({name:'WordbookList'})
      }
    },
    components: {
      Word,
      // WordCreate,
      WordbookFAB
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
</style>