<template>
  <div id="app">
    <h1>App 화면</h1>

    <!-- 비회원일 때 Main 화면 -->
    <div v-if="!isLoggedIn()">
      <Introduction />
    </div>
    
    <!-- 회원일 때 Main 화면 -->
    <div v-else>
      <Home/>
    </div>
  </div>
</template>
<script>
import Home from '@/components/Home.vue'
import Introduction from '@/components/Introduction.vue'
import { mapGetters } from 'vuex'
export default {
  name: 'App',
  components: {
    Home,
    Introduction
  },
  methods:{
    ...mapGetters('Kakao', ['isLoggedIn']),
  },
}
</script>
<style>

</style>
