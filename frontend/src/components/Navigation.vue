<template>
  <div class="navigation-container">
    <v-app-bar color="white" flat>
      <!-- <v-app-bar color="white"> -->

      <!-- <img src="@/assets/michinLogo2.png" width="60px" alt="logo.png"> -->
      <!-- <img src="@/assets/michinLogo.png" width="120px" alt="logo.png">  -->
      <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->

      <!-- <v-toolbar-title>Title</v-toolbar-title> -->
      <!-- <v-icon>mdi-arrow-left</v-icon> -->
      <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->
      <v-spacer></v-spacer>
      <v-toolbar-title>
        <div class="navigation-title">
          <img src="@/assets/michinLogo02.png" width="40px" alt="" />
          <span class="title"> {{ navigationTitle }}</span>
        </div>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <!-- <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
        </v-btn> -->
      <v-btn icon>
        <v-icon @click="logout()" color="var(--font-soft-color)"
          >mdi-power</v-icon
        >
      </v-btn>

      <!-- <template v-slot:extension>
            <v-tabs align-with-title>
            <v-tab router-link to='/conversation'>오늘의 회화</v-tab>   
            <v-tab router-link to='/chatbot/list'>챗봇</v-tab>
            <v-tab router-link to='/wordbook/list'>단어장</v-tab>
            <v-tab router-link to='/score'>내점수</v-tab>
            </v-tabs>
        </template> -->
    </v-app-bar>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";
export default {
  name: "Navigation",
  methods: {
    ...mapActions("Kakao", ["logout"]),
  },
  computed: {
    ...mapGetters(["navigationTitle"]),
  },
};
</script>

<style>
.navigation-container {
  box-shadow: 1px 1px 7px -5px rgb(100, 100, 100);
}

.navigation-title {
  display: flex;
  align-items: center;
}
.navigation-title img {
  margin-right: 5px;
}
</style>