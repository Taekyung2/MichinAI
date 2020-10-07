<template>
  <div class="container">
    <!-- <h2>오늘의 회화</h2> -->
    <ConversationList />
  </div>
</template>

<script>
import ConversationList from "@/components/conversation/ConversationList.vue";
import { mapMutations } from "vuex";
export default {
  name: "Conversation",
  components: {
    ConversationList,
  },
  methods: {
    ...mapMutations(["SET_NAVIGATION_TITLE"]),
  },
  created() {
    this.SET_NAVIGATION_TITLE("오늘의 회화");
  },
};
</script>

<style>
</style>