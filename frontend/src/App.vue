<template>
  <v-app>
    <div id="app">
      <div class="main-container">
        <!-- 비회원일 때 Main 화면 -->
        <div v-if="!isLoggedIn || isMobileLogin">
          <Introduction />
        </div>
        
        <!-- 회원일 때 Main 화면 -->
        <div v-else>
          <Home/>
        </div>
        
      </div>
    </div>
  </v-app>
</template>
<script>
import Home from '@/components/Home.vue'
import Introduction from '@/components/Introduction.vue'

import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'App',
  components: {
    Home,
    Introduction,
  },
  computed :{
    ...mapGetters('Kakao', ['isLoggedIn', 'isMobileLogin']),
  },
  methods: {
    ...mapMutations('Kakao', ['SET_MOBILE_LOGIN'])
  },
  updated(){
    this.SET_MOBILE_LOGIN(localStorage.getItem('userBotKey'))
  },
  created(){
    this.SET_MOBILE_LOGIN(localStorage.getItem('userBotKey'))
  }

}
</script>
<style>
:root{
  --main-color : #7DB3D9;
  --main-sub-color : #F2CC06;
  --main-point-color :  #F48705;
  
  /* --footer-color : #F2CC06; */
  --footer-color : #252525;
  --navigation-color : #7DB3D9;
  --font-base-color : #252525;
  --font-sub-color : #777777;
  --font-soft-color : #a6a6a6;
  --font-point-color : #F48705;
  --hover-background-color : #f2f2f2;
  --border-base-color : #a6a6a6;
}

.container{
   /* background-color: rgb(250, 250, 250); */
}
</style>
