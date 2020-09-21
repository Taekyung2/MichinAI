<template>
  <div>
      <h3>{{ wordbook.id }}번 단어장 Detail 입니다.</h3>
      <p @click="back">뒤로가기</p>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
    name: 'WordbookDetail',
    computed: {
      ...mapGetters([
        'wordbook',
      ])
    },
    methods: {
      back(){
        this.$router.push({name:'WordbookList'})
      }
    }
}
</script>

<style>

</style>