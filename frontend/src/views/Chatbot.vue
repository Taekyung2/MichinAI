<template>
  <div class="container">
    <router-view />
  </div>
</template>

<script>
import { mapMutations } from "vuex";
export default {
  name: "Chatbot",
  methods: {
    ...mapMutations(["SET_NAVIGATION_TITLE"]),
  },
  created() {
    this.SET_NAVIGATION_TITLE("채팅기록");
  },
};
</script>

<style>
</style>