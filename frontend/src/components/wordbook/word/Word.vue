<template>
  <!-- <div class='word-container'>
    <div class="my-2">
    <div class="word">
    <span class="word-eng eng-text" :class="isShowEng? 'word-show': 'word-noshow'" >

      {{ word.eng }} </span> 
    
    <v-scroll-y-transition>
    <span :class="isShowKor? 'word-show': 'word-noshow'" class ="word-kor"> {{ word.kor }} </span>
    </v-scroll-y-transition>
    </div>
    
    <v-btn color="success" fab x-small dark >
        <v-icon>mdi-pencil</v-icon>
    </v-btn>
    <v-btn color="error" fab x-small dark >
        <v-icon>mdi-minus</v-icon>
    </v-btn> -->
    <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-card-text>
      <div style="float:left; width:10%">
      <v-icon v-if="!word.check" style="color:var(--border-base-color)">mdi-bookmark-outline</v-icon> 
      <v-icon v-if="word.check" style="color:var(--main-sub-color)">mdi-bookmark</v-icon>
      </div>
      <p class="display-1 text--primary" style="float:0; width:80%">
        {{word.eng}}
      </p>
      <div>{{word.kor}}</div>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "Word",
  props: {
    word: Object,
    isShowKor: Boolean,
    isShowEng: Boolean,
  },
};
</script>

<style>
.word-container {
  border: 1px solid var(--border-base-color);
  border-radius: 3px;
  /* border-left: 7px solid var(--main-point-color); */
  /* box-shadow: 0 0 0 3px green inset; */
}

.word {
  display: flex;
  justify-content: space-between;
  margin: auto 10px;
  align-items: center;
}
.word-eng {
  width: 60%;
  text-align: left;
  word-break: break-all;
  font-size: 1.1rem;
}
.word-kor {
  margin-left: 10px;
  font-size: 0.85rem;
  color: var(--font-sub-color);
  text-align: right;
  word-break: keep-all;
}
.word-show {
  color: var(--font-base-color);
  transition-duration: 1s;
}
.word-noshow {
  color: white;
  transition-duration: 0.3s;
}
</style>