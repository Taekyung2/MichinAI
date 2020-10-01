<template>
    <div class="home-container">
        <div class="navigation-container">
            <Navigation/>
        </div>
        <div id="home-router-container">
            <router-view />
        </div>
        <div class="footer-container">
            <Footer/>
        </div>
  
    </div>
</template>

<script>
import { mapActions } from 'vuex'
import Navigation from '@/components/Navigation.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: 'Home',
    methods:{
        ...mapActions('Kakao', ['logout']),
    },
    created() {
        this.$router.push({name: 'Conversation'})
    },
    components:{
        Navigation,
        Footer,
    }

}
</script>

<style>
.footer-container{
  margin-top: auto;
}
</style>