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
