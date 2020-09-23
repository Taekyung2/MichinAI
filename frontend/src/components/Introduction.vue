<template>
    <div class="intro-container">
        <h1>비회원 전용 Main화면 입니다.</h1>
        <KakaoLogin/>
    </div>

</template>

<script>
import KakaoLogin from '@/components/account/KakaoLogin.vue'
export default {
    name: 'Introduction',
    components:{
        KakaoLogin,
    }
}
</script>

<style>

</style>