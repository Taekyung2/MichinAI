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

*{ 
  font-family: 'NanumSquare', sans-serif !important; 
  /* font-family: 'BMJUA'; */
}


.eng-text{
  font-family: 'NanumSquareRound';
}
/* 글꼴 머하징 흑ㅎㄱ */
@font-face { font-family: 'Bazzi'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/Bazzi.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'yg-jalnan'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_four@1.2/JalnanOTF00.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'BMJUA'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'BMDOHYEON'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMDOHYEON.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'InfinitySans-RegularA1'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/InfinitySans-RegularA1.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'NanumSquareRound'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_two@1.0/NanumSquareRound.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'YESGothic-Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_13@1.0/YESGothic-Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
</style>
