<template>
  <div class='word-container'>
    <div class="my-2">
    <div class="word">
    <span class="word-eng" :class="isShowEng? 'word-show': 'word-noshow'" >
      <v-icon v-if="!word.check"  style="color:var(--border-base-color)">mdi-star-outline</v-icon> 
      <v-icon v-if="word.check" style="color:var(--main-sub-color)">mdi-star</v-icon>
      {{ word.eng }} </span> 
    
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
.word-eng{
  width: 60%;
  text-align: left;
  word-break:break-all;
  font-size: 18px;
}
.word-kor{
  margin-left: 10px;
  font-size: 13px;
  color: var(--font-sub-color);
  text-align: right;
  word-break: keep-all;
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