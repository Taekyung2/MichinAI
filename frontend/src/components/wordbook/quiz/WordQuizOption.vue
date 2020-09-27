<template>
    <v-dialog
    :value="isSelectedQuizOption"
    max-width="500px"
    persistent 
    
    >
    <v-card
    >
        <v-toolbar dark color="primary">
            <v-btn icon dark @click="SET_SELECTED_QUIZ_OPTION">
            <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title> 퀴즈 옵션</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>
        <v-card-text>
            
            퀴즈 종류 | OX , 객관식(뜻 선택)<hr>
            시간 | 최대 30초, 3초 5초 10초 15초 20초 30초 <hr>
            단어 개수 | 전체, 20개, 사용자 지정<hr>
            순서 | 순서대로, 랜덤<hr>

            <v-btn
                color="primary"
                dark
                @click="openQuiz"
            >
                START
            </v-btn>

        </v-card-text>
        <div v-if="isStartedQuiz">
        <WordQuiz :wordList="wordList"/>
        </div>
    </v-card>
    </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import WordQuiz from '@/components/wordbook/quiz/WordQuiz.vue'
export default {
    name: 'WordQuizOption',
    computed:{
        ...mapGetters([
            'isSelectedQuizOption',
            'isStartedQuiz',
        ]),
    },
    props:{
        wordList: Array,
    },
    components:{
        WordQuiz,
    },
    methods:{
        ...mapMutations([
            'SET_SELECTED_QUIZ_OPTION',
            'SET_STARTED_QUIZ'
        ]),
        openQuiz(){
            this.SET_STARTED_QUIZ()
        }
       
    },

}
</script>

<style>

</style>