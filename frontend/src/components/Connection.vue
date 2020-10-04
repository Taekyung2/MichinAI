<template>
    <div class="intro-container">
        <h1>Kakao 연동 화면입니다.</h1>
        <KakaoLogin/>
    </div>

</template>

<script>
import KakaoLogin from '@/components/account/KakaoLogin.vue'
export default {
    name: 'Connection',
    components:{
        KakaoLogin,
    }
}
</script>

<style>

</style>