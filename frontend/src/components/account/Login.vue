<template>
    <div class="intro-container">
        <h1>비회원 로그인 화면입니다.</h1>
        <KakaoLogin/>
        <Introduction/>
    </div>

</template>

<script>
import KakaoLogin from '@/components/account/KakaoLogin.vue'
import Introduction from '@/components/Introduction.vue'

export default {
    name: 'Login',
    components:{
        KakaoLogin,
        Introduction
    }
}
</script>

<style>

</style>