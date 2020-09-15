<template>
  <div id="app">
    <h1>App 화면</h1>
    <!-- 비회원일 때 Main 화면 -->
    <Intro />

    <!-- 회원일 때 Main 화면 -->
    <Home/>
  </div>
</template>
<script>
import Home from '@/components/Home.vue'
import Intro from '@/components/Intro.vue'

export default {
  name: 'App',
  components: {
    Home,
    Intro

  }
}
</script>
<style>

</style>
