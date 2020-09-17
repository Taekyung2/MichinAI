<template>
<div>
    <a id="custom-login-btn" @click="login()">
        <img
            src="//k.kakaocdn.net/14/dn/btqCn0WEmI3/nijroPfbpCa4at5EIsjyf0/o.jpg"
            width="222"
        />
    </a>
</div>
  
</template>

<script>
// import Kakao from '@/oauth/kakao.js'
import { mapActions } from 'vuex'

export default {
    name : 'KakaoLogin',
    methods:{
        ...mapActions('Kakao', ['init','login']),
    },
}
</script>

<style>

</style>