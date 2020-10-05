<template>
  <div>
    <v-card outlined @click="selectedWordbook(wordbook)">
      <h3 align center>level {{ wordbook.level }}</h3>
    </v-card>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "BaseWordbookListItem",
  props: {
    wordbook: Object,
  },
  methods: {
    ...mapActions(["selectedWordbook"]),
  },
};
</script>

<style>
</style>