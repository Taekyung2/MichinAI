<template>
    <div class="intro-container">
        <div class="login-container" align="center">
            <div class="login-text" align="left">
                <p>스마트한 Chat-Bot,</p>
                <p><span class="point-name">미친AI</span>와 함께</p>
                <p>미쳐버리는</p>
                <p><span class="point-eng">미친영어</span> 공부법</p>
            </div>
            <div class="login-logo-container">
                <img class="login-logo" width="55%" src="@/assets/michinLogo03.svg" alt="michinLogo03.svg">                   
            </div>
            <div class="login-bottom-container">
                <div class="login-sub-text">
                    <p>지금 바로!</p>
                    <p class="text-click">Click</p>
                    <img class="pointer-icon" src="@/assets/icon/pointer.png" color="white" width="35px" alt="pointer.png">
                </div>
                <KakaoLogin :eventName="eventName"/>
            </div>
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
    name: 'Login',
    components:{
        KakaoLogin,
        Introduction
    },
    data(){
        return {
            eventName: 'login',
        }
    },
}
</script>

<style scoped>
.login-container{
    height: 100vh;
    width: 100%;
    background: var(--main-sub-color);
    display: flex;
    flex-direction: column;
    padding: 50px 30px;
    justify-content: space-between;
}
.login-logo-container{
    display: flex;
    height: 100%;
    align-items: flex-end;
    justify-content: center;
}
.login-logo{
    padding-bottom: 10px;
    flex: 1 1 auto;
    margin-left: -13px;
}
@media (min-width:0px) and (max-width: 600px) {
    .login-container{
        padding: 50px 30px 50px 30px;
    }
    .login-logo{
        max-height: 230px;
        max-width: 230px;
    }
}
@media (min-width:601px)  and (max-width: 1024px) {
    .login-container{
        padding: 50px 30px;
    }
    .login-logo{
        max-height: 200px;
        max-width: 200px;
    }
}
@media (min-width:1025px)  and (max-width: 1904px) {
    .login-container{
        padding: 50px 100px;
    }
    .login-logo{
        max-height: 200px;
        max-width: 200px;
    }
}
.login-text *{
    font-family: 'BMDOHYEON' !important;
    font-size: 1.75rem;
    margin-bottom: 0px;
}
.login-sub-text p{
    font-family: 'BMDOHYEON' !important;
    font-size: 1.3rem;
    margin-bottom: 0px;
}
.login-sub-text .text-click{
    font-family: 'BMDOHYEON' !important;
    font-size: 1.1rem;
    margin-bottom: 0px;
    margin-top: 10px;
    color: var(--main-color)
}
.login-text span{
    font-size: 1.5em;
    margin-left: -2px;
}
.point-name{
    color: var(--main-color);
}

.point-eng{
    color: var(--main-point-color);
}
.login-bottom-container{
    margin-top: auto;
    /* margin-bottom: 30px; */
}

.pointer-icon{
    margin-bottom: 10px;
}
</style>