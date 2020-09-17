<template>
    <div class="container">
        <h1>회원 전용 Main화면 입니다.</h1>
        <button @click="logout()">로그아웃</button>

        <div id="nav">
            <router-link to='/chatbot'>챗봇 | </router-link>
            <router-link to='/wordbook'>단어장 | </router-link>
            <router-link to='/score'>내점수</router-link>
        
        </div>
        <router-view/>
    </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
    name: 'Home',
     methods:{
        ...mapActions('Kakao', ['logout']),
    },
}
</script>

<style >

</style>