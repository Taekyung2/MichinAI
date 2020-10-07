<template>
  <div class="container">
    <!-- <h2>단어장 화면</h2>   
        <hr> -->
    <router-view />
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "Wordbook",
  methods: {
    ...mapMutations(["SET_NAVIGATION_TITLE"]),
  },
  created() {
    this.SET_NAVIGATION_TITLE("단어장");
  },
};
</script>

<style>
</style>