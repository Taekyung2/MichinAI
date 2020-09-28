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
        <div v-if="quizOption.selectedTime != '제한없음' ">
            <h3>시간</h3>
        </div>

        <div>
            <WordQuizQuestion :eng="wordList[0].eng"/>
            <WordQuizAnswer :kor="wordList[0].kor"/>
            <hr>
        </div>
        <v-divider></v-divider>
        <div>
             <v-btn
                class="mx-2"
                fab
                dark
                large
                color="var(--main-color)"
                elevation="2"
                >
                <v-icon dark>
                    {{ playIcon[0] }}
                </v-icon>
            </v-btn>
        </div>
        </v-card>
    </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import WordQuizQuestion from '@/components/wordbook/quiz/WordQuizQuestion.vue'
import WordQuizAnswer from '@/components/wordbook/quiz/WordQuizAnswer.vue'

export default {
    name: 'WordQuiz',
    data(){
        return{
            playIcon: ['mdi-play','mdi-pause'],
            timer : 0,
            quizWordList: [],
        }
    },
    props:{
        selectAll: Boolean,
        wordList: Array,
        quizOption: Object,
    },
    components:{
        WordQuizQuestion,
        WordQuizAnswer
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
        
        async initSequence(){
            if(this.quizOption.selectedSequence == "랜덤"){
                this.quizWordList = await this.wordList.sort(function(){
                    return 0.5 - Math.random()
            })}
            else
                this.quizWordList = this.wordList
        },
        initTime(){
            if(this.quizOption.selectedTime == "제한없음")
                this.timer = null
            else{
                let time = this.quizOption.selectedTime
                this.timer = time.substring(0, time.length-1)
            }
        },
        initWordList(){
            let questionNumber = this.quizOption.selectedQuestionNumber;
            if(questionNumber.type == '사용자 정의'){
                console.log(questionNumber.number)
                this.quizWordList = this.wordList.slice(0,questionNumber.number)
            }
            else if(questionNumber.type == '미암기 단어'){
                let list = []
                for(let i=0; i< this.wordList.length; i++){
                    if(!this.wordList[i].check)
                        list.push(this.wordList[i])
                }
               this.quizWordList = [...list]
            }
            else{
                this.quizWordList = this.wordList
            }
        },
        async initOption(){
            await this.initSequence()
            this.initTime()
            this.initWordList()
        }
    },

    created(){
        console.log(this.quizOption)
    },
    mounted(){
        this.initOption()
    }
}
</script>

<style>

</style>