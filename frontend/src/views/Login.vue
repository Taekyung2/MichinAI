<template>
    <div class="login-container">
        <h1>Login 화면 입니다.</h1>
        <KakaoLogin/>
    </div>

</template>

<script>
import KakaoLogin from '@/components/account/KakaoLogin.vue'
import { mapMutations } from 'vuex'
export default {
    name: 'Login',
    components:{
        KakaoLogin,
    },
    methods:{
        ...mapMutations([
            'SET_USERBOT_KEY',
        ]),
    },
    created(){
        this.SET_USERBOT_KEY(localStorage.getItem('userBotKey'))
    }
}
</script>

<style>

</style>