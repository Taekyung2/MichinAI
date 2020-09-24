<template>
    <v-dialog v-model="quizAll" fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card>
    <v-toolbar dark color="primary">
        <v-btn icon dark @click="SET_SELECTED_QUIZ_ALL">
        <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>퀴즈</v-toolbar-title>
        <v-spacer></v-spacer>
    </v-toolbar>
    <v-list three-line subheader>
        <v-subheader>User Controls</v-subheader>
        <v-list-item>
        <v-list-item-content>
            <v-list-item-title>Content filtering</v-list-item-title>
            <v-list-item-subtitle>Set the content filtering level to restrict apps that can be downloaded</v-list-item-subtitle>
        </v-list-item-content>
        </v-list-item>
        <v-list-item>
        <v-list-item-content>
            <v-list-item-title>Password</v-list-item-title>
            <v-list-item-subtitle>Require password for purchase or use password to restrict purchase</v-list-item-subtitle>
        </v-list-item-content>
        </v-list-item>
    </v-list>
    <v-divider></v-divider>
    </v-card>
    </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'

export default {
    name: 'WordQuizAll',
    data(){
        return{
        ss : true,
        }
    },
    props:{
        selectAll : Boolean
    },
    computed: {
      ...mapGetters([
        'quizAll',
      ]),
    },
    methods: {
        ...mapMutations([
            'SET_SELECTED_QUIZ_ALL',
        ])
    }
}
</script>

<style>

</style>