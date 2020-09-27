<template>
    <v-dialog v-model="isStartedQuiz" fullscreen hide-overlay @click.stop transition="dialog-bottom-transition">
        <v-card>
        <v-toolbar dark color="primary">
            <v-btn icon dark @click="SET_STARTED_QUIZ">
            <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>퀴즈</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>
        <div v-for="(word,index) in wordList" :key="index">
            {{word.eng}} | {{word.kor}}
        
        </div>
        <v-divider></v-divider>
        </v-card>
    </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'

export default {
    name: 'WordQuiz',
    data(){
        return{
        ss : true,
        }
    },
    props:{
        selectAll : Boolean,
        wordList : Array,
    },
    computed: {
      ...mapGetters([
        'isStartedQuiz',
      ]),
    },
    methods: {
        ...mapMutations([
            'SET_SELECTED_QUIZ_OPTION',
            'SET_STARTED_QUIZ',
        ]),
    }
}
</script>

<style>

</style>