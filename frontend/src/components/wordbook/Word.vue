<template>
  <div class='word-container'>
    <div class="my-2">
    <div class="word">
    <span :class="isShowEng? 'word-show': 'word-noshow'" > {{ word.eng }} </span> 
    
    <v-scroll-y-transition>
    <span :class="isShowKor? 'word-show': 'word-noshow'" class ="word-kor"> {{ word.kor }} </span>
    </v-scroll-y-transition>
    </div>
    
    <!-- <v-btn color="success" fab x-small dark >
        <v-icon>mdi-pencil</v-icon>
    </v-btn>
    <v-btn color="error" fab x-small dark >
        <v-icon>mdi-minus</v-icon>
    </v-btn> -->
    </div>

      
  </div>
</template>

<script>
export default {
    name: 'Word',
    props: {
      word: Object,
      isShowKor: Boolean,
      isShowEng: Boolean
    },
}
</script>

<style>
.word-container{
    border: 1px solid var(--border-base-color);
    border-radius: 3px;
}

.word{
  display: flex;
  justify-content: space-between;
  margin : auto 10px;
  font-size: 20px;
  align-items: center;
}
.word-kor{
  font-size: 14px;
  color: var(--font-base-color);
}
.word-show{
  color: var(--font-base-color);
  transition-duration: 1s;
}
.word-noshow{
  color: white;
  transition-duration: 0.3s;
}
</style>