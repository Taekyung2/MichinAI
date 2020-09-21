<template>
<div>
  <li @click="selectedWordbook(wordbook)">
    {{wordbook.id}}번째, 단어장 아이템 입니다.
  </li>
</div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'WordbookListItem',
    props: {
      wordbook: Object,
    },
    methods: {
      ...mapActions(['selectedWordbook']),
    }
}
</script>

<style>

</style>