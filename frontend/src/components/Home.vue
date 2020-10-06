<template>
    <div class="home-container">
        <div class="navigation-container">
            <Navigation/>
        </div>
        <div class="home-router-container">
            <router-view />
        </div>
        <div class="footer-container">
            <Footer/>
        </div>

        <v-snackbar
            v-once
            :value="isSuccessMobileConnection"
            outlined
            class="connection-snackbar"
            timeout="2500"
            :color="isMobileConnected.state?'success':'error'"
            >
            <v-icon v-if="isMobileConnected.state" color="success">mdi-checkbox-marked-circle</v-icon>
            <v-icon v-else color="error">mdi-account-alert-outline</v-icon> 
            <span class="connection-message">{{ isMobileConnected.message }}</span>

            <template v-slot:action="{ attrs }">
                <v-btn
                :color="isMobileConnected.state?'success':'error'"
                text
                v-bind="attrs"
                >
                Close
                </v-btn>
            </template>
        </v-snackbar>

  
    </div>
</template>

<script>

import Navigation from '@/components/Navigation.vue'
import Footer from '@/components/Footer.vue'
import { mapGetters } from 'vuex'

export default {
    name: 'Home',

    created() {
        this.$router.push({name: 'Conversation'})
    },
    components: {
        Navigation,
        Footer,
    },
    computed: {
        ...mapGetters('Kakao',[
            'isSuccessMobileConnection',
            'isMobileConnected',
        ]),
    },

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
   margin-bottom: 50px;
}

.footer-container{
  margin-top: auto;
}
.connection-message{
    margin-left: 10px;
}

</style>