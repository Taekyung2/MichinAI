<template>
  <div class="ox-container">
    <div class="ox-btn">
      <v-btn fab dark x-large :color="oxColor[index]" v-for="(ox, index) in oxValue" :key="index" class="mx-5 my-10">
        <v-icon
          class="ox-icon"
          @click="submitUserAnswer"
          :value="oxValue[index]"
          >{{ oxIcon[index] }}</v-icon
        >
      </v-btn>
    </div>
  </div>
</template>

<script>
export default {
  name: "WordQuizTypeOX",
  data() {
    return {
      oxColor: ["#1E88E5", "#F4511E"],
      oxValue: ["O", "X"],
      oxIcon: ["mdi-circle-outline", "mdi-close"],
    };
  },
  methods: {
    submitUserAnswer() {
      this.$emit("next-ox-quiz", event.target.value);
    },
  },
};
</script>

<style scoped>
/* .ox-container {
  display: flex;
}
.ox-btn {
  flex-basis: 50%;
} 
.v-btn {
  width: 100%;
}
.ox-icon {
  width: 100%;
}*/
.v-icon.v-icon::after {
  border-radius: 0px;
  background-color: transparent !important;
}
.disabled {
  pointer-events: none;
}
</style>