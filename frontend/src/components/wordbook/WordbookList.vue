<template>
  <div>
      <h3>단어장 리스트 입니다.</h3>
      <WordbookListItem 
        :wordbook="wordbook"
        v-for="wordbook in wordbookList" :key="wordbook.id"/>

  </div>
</template>

<script>
import WordbookListItem from '@/components/wordbook/WordbookListItem.vue'
export default {
    name: 'WordbookList',
    data() {
      return {
        wordbookList : [
            {id:'1'},
            {id:'2'}
        ],
      }
    },
    components: {
      WordbookListItem,
    }

}
</script>

<style>

</style>