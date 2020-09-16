<template>
<div>
    <a id="custom-login-btn" @click="loginWithKakao">
        <img
            src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
            width="222"
        />
    </a>
    <button @click="logoutWithKakao">로그아웃</button>
    <p id="token-result"></p>
</div>
  
</template>

<script>
import Kakao from '@/oauth/kakao.js'
export default {
    name : 'KakaoLogin',
    methods:{
        loginWithKakao(){
            Kakao.Login()
        },
        logoutWithKakao(){
            Kakao.Logout()
        }
    },
    created(){
        Kakao.Init()
    }
   
}
</script>

<style>

</style>