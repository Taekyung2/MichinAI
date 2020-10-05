<template>
    <div class="intro-container">
        <h1>Kakao 연동 화면입니다.</h1>
        <KakaoLogin/>
        <Introduction/>
    </div>

</template>

<script>
import KakaoLogin from '@/components/account/KakaoLogin.vue'
import Introduction from '@/components/Introduction.vue'

export default {
    name: 'Connection',
    components:{
        KakaoLogin,
        Introduction
    }
}
</script>

<style>

</style>