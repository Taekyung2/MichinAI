<template>
<div>
    <div v-if="eventName=='login'">
        <v-btn
        tile
        color="#3E2622"
        class="login-btn"
        justify="space-around"
        width="100%"
        @click="login()"
        >
        <span class="login-text-kakao"> Login with Kakao</span>
        </v-btn>
    </div>
       <div v-else>
        <v-btn
        tile
        color="#3E2622"
        class="login-btn"
        justify="space-around"
        width="100%"
        @click="login()"
        >
        <span class="login-text-kakao"> Connect with Kakao</span>
        </v-btn>
    </div>
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
    props:{
        eventName: String,
    }
}
</script>

<style>
.login-text-kakao{
    font-size: 1.2em;
    font-family: 'BMDOHYEON' !important;
    color: #FAED38;
}
.login-btn{
    border-radius: 10px;
    padding: 25px 0px !important;
}
</style>