<template>
    <div class="container">
        <h1>회원 전용 Main화면 입니다.</h1>
        <div id="nav">
            <router-link to='/chatbot'>챗봇 | </router-link>
            <router-link to='/wordbook'>단어장 | </router-link>
            <router-link to='/score'>내점수</router-link>
        
        </div>
        <router-view/>
    </div>
</template>

<script>
export default {
    name: 'Home'
}
</script>

<style >

</style>