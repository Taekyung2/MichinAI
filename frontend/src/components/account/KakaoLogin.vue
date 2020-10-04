<template>
<div>
    <a id="custom-login-btn" @click="login()">
        <img
            src="@/assets/kakao_logo.png"
            width="80"
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