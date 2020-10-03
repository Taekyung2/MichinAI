<template>
    <div class="home-container">
        <v-system-bar
        dark
        color="primary"
        >
        <v-spacer></v-spacer>
        <v-icon>mdi-wifi-strength-4</v-icon>
        <v-icon>mdi-signal-cellular-outline</v-icon>
        <v-icon>mdi-battery</v-icon>
        <span>12:30</span>
        </v-system-bar>
        
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

import Navigation from '@/components/Navigation.vue'
import Footer from '@/components/Footer.vue'

export default {
    name: 'Home',

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
.navigation-container{
    position: sticky;
    top: 0;
    z-index: 1;
}
.home-router-container{
   background-color: rgb(250, 250, 250);
}

.footer-container{
  margin-top: auto;
}
</style>