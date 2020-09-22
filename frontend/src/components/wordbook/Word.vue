<template>
  <div class='word-container'>
      <span>{{ word.id}} {{ word.eng }} | {{ word.kor }} </span>
      
  </div>
</template>

<script>

export default {
    name: 'Word',
    props: {
      word: Object,
    },

}
</script>

<style>
.word-container{
    border: 1px solid gray;
    border-radius: 3px;
}
</style>