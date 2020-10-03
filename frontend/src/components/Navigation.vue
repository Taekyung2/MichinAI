<template>
   <v-card >
        <v-app-bar
        color="var(--main-sub-color)"
        dark
        >
        <img src="@/assets/michinLogo2.png" width="60px" alt="logo.png">
        <!-- <img src="@/assets/michinLogo.png" width="120px" alt="logo.png">  -->
        <!-- <v-app-bar-nav-icon></v-app-bar-nav-icon> -->

        <!-- <v-toolbar-title>Title</v-toolbar-title> -->
        <!-- <v-icon>mdi-arrow-left</v-icon> -->
        <v-toolbar-title class="title"> Title</v-toolbar-title>
        <v-spacer></v-spacer>

        <!-- <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
        </v-btn> -->
        <v-btn icon >
            <v-icon @click="logout()">mdi-export</v-icon>
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
    </v-card>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'Navigation',
    methods:{
        ...mapActions('Kakao', ['logout']),
    },
}
</script>

<style scoped>
.v-sheet.v-app-bar.v-toolbar:not(.v-sheet--outlined) {
    box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 2px 4px 0px rgb(0 0 0 / 0%), 0px 1px 5px 0px rgb(0 0 0 / 0%);
}
.title{
    font-family: 'NanumSquareRound';
    margin-left: 10px;
}
</style>