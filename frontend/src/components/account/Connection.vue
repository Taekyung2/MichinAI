<template>
    <div>
        <div class="connect-container">
            <h1>Kakao 연동 화면입니다.</h1>
            <KakaoLogin/>
        </div>
        <div class="intro-container">
            <Introduction/>
        </div>
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
.connect-container{
    height: 100vh;
    width: 100%;
    background: var(--main-sub-color);
    display: flex;
    flex-direction: column;
    padding: 50px 30px;
    justify-content: space-between;
}
</style>