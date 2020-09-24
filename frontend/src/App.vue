<template>
  <v-app>
    <div id="app">
      <div class="main-container">
        <!-- 비회원일 때 Main 화면 -->
        <div v-if="!isLoggedIn()">

          <Introduction />
        </div>
        
        <!-- 회원일 때 Main 화면 -->
        <div v-else>
          <Home/>
        </div>
        
      </div>

      <div class="footer-container">
        <Footer/>
      </div>
      
    </div>
  </v-app>
</template>
<script>
import Home from '@/components/Home.vue'
import Introduction from '@/components/Introduction.vue'
import Footer from '@/components/Footer.vue'

import { mapGetters } from 'vuex'
export default {
  name: 'App',
  components: {
    Home,
    Introduction,
    Footer,
  },
  methods:{
    ...mapGetters('Kakao', ['isLoggedIn']),
  },
  
}
</script>
<style>
:root{
  --red : #DC3545;
  --main-color : #7DB3D9;
  --main-sub-color : #F2CC06;
  --main-point-color :  #F48705;
  
  /* --footer-color : #F2CC06; */
  --footer-color : #252525;
  --navigation-color : #7DB3D9;
  --font-base-color : #252525;
  --font-soft-color : #a6a6a6;
  --font-point-color : #F48705;
  --hover-background-color : #f2f2f2;
  --border-base-color : #a6a6a6;
}

#app{
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
#nav{
  margin-bottom: auto;
}
.footer-container{
  margin-top: auto;
}
</style>
