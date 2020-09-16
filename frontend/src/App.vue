<template>
  <div id="app">
    <h1>App 화면</h1>
    <!-- 비회원일 때 Main 화면 -->
    <Introduction />

    <!-- 회원일 때 Main 화면 -->
    <Home/>
  </div>
</template>
<script>
import Home from '@/components/Home.vue'
import Introduction from '@/components/Introduction.vue'

export default {
  name: 'App',
  components: {
    Home,
    Introduction
  }
}
</script>
<style>

</style>
