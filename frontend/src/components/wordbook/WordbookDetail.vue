<template>
  <div>
      <h3>{{ wordbook.id }}번 단어장 Detail 입니다.</h3>
      <Word 
        :word="word"
        v-for="word in wordList" :key="word.id"/>
          
      <WordCreate/>
      <p @click="back">뒤로가기</p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Word from '@/components/wordbook/Word.vue'
import WordCreate from '@/components/wordbook/WordCreate.vue'
export default {
    name: 'WordbookDetail',
    data() {
      return {
        wordList : [
            {id:'1', eng: '영어1', kor: '뜻1'},
            {id:'2', eng: '영어2', kor: '뜻2'}
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
      WordCreate,
    }
}
</script>

<style>

</style>