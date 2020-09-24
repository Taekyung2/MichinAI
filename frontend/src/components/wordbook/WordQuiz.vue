<template>
    <v-dialog
    :value="isSelectedQuiz"
    max-width="500px"
    
    >
    <v-card
    v-click-outside="closeQuiz"
    >
        <v-card-title>
        퀴즈 선택
        </v-card-title>
        <v-card-text>
        <v-btn
            color="primary"
            dark
            @click="openQuizAll"
        >
            ALL
        </v-btn>

        <v-btn
            color="primary"
            dark
        >
            Random
        </v-btn>
        </v-card-text>
        <div v-if="isSelectedQuizAll">
        <WordQuizAll :wordList="wordList"/>
        </div>
    </v-card>
    </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import WordQuizAll from '@/components/wordbook/WordQuizAll.vue'
export default {
    name: 'WordQuiz',
    computed:{
        ...mapGetters([
            'isSelectedQuiz',
            'isSelectedQuizAll',
        ]),
    },
    props:{
        wordList: Array,
    },
    components:{
        WordQuizAll,
    },
    methods:{
        ...mapMutations([
            'SET_SELECTED_QUIZ_ALL',
            'SET_SELECTED_QUIZ'
        ]),
        openQuizAll(){
            this.SET_SELECTED_QUIZ_ALL()
        },
        closeQuiz(){
            this.SET_SELECTED_QUIZ()
        }
    },

}
</script>

<style>

</style>