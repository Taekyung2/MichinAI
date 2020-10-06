<template>
    <div>
        <div class="connect-container">
            <div>
                <h1>미친AI <span style="font-size:1.5rem"> X </span> Kakao</h1>
                <h2>미친AI와 Kakao를 연동해 보세요!</h2>
                <h2>오늘의 회화, 영어 문법 교정 및 통계화 등 다양한 서비스를 이용하실 수 있습니다.</h2>
            </div>
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
.connect-container > h1{
    font-size: 2.5rem;
}

@media (min-width:0px) and (max-width: 600px) {
    .connect-container{
        padding: 50px 30px 50px 30px;
    }
}
@media (min-width:601px)  and (max-width: 1024px) {
    .connect-container{
        padding: 50px 30px;
    }
}
@media (min-width:1025px)  and (max-width: 1904px) {
    .connect-container{
        padding: 50px 100px;
    }
}
</style>