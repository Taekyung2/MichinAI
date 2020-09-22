<template>
    <div>
    <v-card class="nav">
        <v-app-bar
        color="#6A76AB"
        dark
        prominent
        >
        <v-app-bar-nav-icon></v-app-bar-nav-icon>

        <v-toolbar-title>Title</v-toolbar-title>

        <v-spacer></v-spacer>

        <v-btn icon>
            <v-icon>mdi-magnify</v-icon>
        </v-btn>

        <v-btn icon>
            <v-icon @click="logout()">mdi-power-standby</v-icon>
        </v-btn>

        <template v-slot:extension>
            <v-tabs align-with-title>
            <v-tab router-link to='/conversation'>오늘의 회화</v-tab>   
            <v-tab router-link to='/chatbot/list'>챗봇</v-tab>
            <v-tab router-link to='/wordbook/list'>단어장</v-tab>
            <v-tab router-link to='/score'>내점수</v-tab>
            </v-tabs>
        </template>
        </v-app-bar>
    </v-card>
    <div id="home-container">
        <router-view/>
    </div>
  
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'Home',
     methods:{
        ...mapActions('Kakao', ['logout']),
    },
    created() {
        this.$router.push({name: 'Conversation'})
    },

}
</script>

<style>
.nav{
    position: sticky;
    top: 0;
}
</style>