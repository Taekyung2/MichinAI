<template>
  <div class='word-container'>
    <div class="my-1">
    <span>{{ word.id}} {{ word.eng }} | {{ word.kor }} </span>
    <v-btn color="success" fab x-small dark >
        <v-icon>mdi-pencil</v-icon>
    </v-btn>
    <v-btn color="error" fab x-small dark >
        <v-icon>mdi-minus</v-icon>
    </v-btn>
    </div>
      
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