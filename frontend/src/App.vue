<template>
  <v-app>
    <div id="app">
      <div class="main-container">

        <!-- 카카오 연동 화면 -->
        <div v-if="isMobileConnection">
          <Connection />
        </div>
                <!-- 회원일 때 Main 화면 -->
        <div v-else-if="isLoggedIn">
          <Home/>
        </div>

        <!-- 비회원일 때 Main 화면 (로그인 화면) -->
        <div v-else>
          <Login />
        </div>

      </div>
    </div>
  </v-app>
</template>
<script>
import Home from '@/components/Home.vue'
import Connection from '@/components/account/Connection.vue'
import Login from '@/components/account/Login.vue'

import { mapGetters, mapMutations } from 'vuex'
export default {
  name: 'App',
  components: {
    Home,
    Connection,
    Login,
    
  },
  computed :{
    ...mapGetters('Kakao', ['isLoggedIn', 'isMobileConnection']),
  },
  methods: {
    ...mapMutations('Kakao', ['SET_MOBILE_CONNECTION'])
  },
  created(){
    this.SET_MOBILE_CONNECTION(localStorage.getItem('userBotKey'))
  }
}
</script>
<style>

@media (min-width:0px) and (max-width: 400px) {
	html{
    font-size: 14px;
  }
}
@media (min-height:0px) and (max-height: 700px) {
	html{
    font-size: 12px;
  }
}
@media (min-width:401px) and (max-width: 600px) {
	html{
    font-size: 15px;
  }
}
@media (min-width:601px) and (max-width: 960px) {
	html{
    font-size: 18px;
  }
}
@media (min-width:961px) and (max-width: 1264px) {
	html{
    font-size: 20px;
  }
}
@media (min-width:1265px) and (max-width: 1904px) {
	html{
    font-size: 18px;
  }
}
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
  color: var(--font-base-color);
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
