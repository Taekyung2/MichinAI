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

            <div class="quiz-container">
                <div v-if="quizOption.selectedTime != '제한없음' ">
                    <h3>시간</h3>
                </div>

                <div>
                    <h1>{{quizWordList[0].eng}}</h1>
                    <v-divider></v-divider>
                    <h4>{{quizWordList[0].kor}}</h4>
                </div>
                <div v-if="quizType=='OX'" class="quiz-type-container">
                    <TypeOX/>
                </div>
                <div v-else class="quiz-type-container">
                    <TypeChoiceKor :choiceKorList="choiceKorList"/>
                </div>
                
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
            </div>
        </v-card>
    </v-dialog>
</template>

<script>
import { mapGetters, mapMutations } from 'vuex'
import TypeChoiceKor from '@/components/wordbook/quiz/WordQuizTypeChoiceKor.vue'
import TypeOX from '@/components/wordbook/quiz/WordQuizTypeOX.vue'

export default {
    name: 'WordQuiz',
    data(){
        return{
            playIcon: ['mdi-play','mdi-pause'],
            timer: 0,
            quizWordList: [
                {id:'', eng: ' ', kor: '', check: false}
            ],
            quizType: this.quizOption.selectedType,
            choiceKorList: [],

        }
    },
    props:{
        selectAll: Boolean,
        wordList: Array,
        quizOption: Object,
    },
    components:{
        TypeChoiceKor,
        TypeOX
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
            console.log(this.quizWordList)
        },
        
        setChoiceKorList(questionWord){
            let randomList = []
            this.choiceKorList.push(questionWord.kor)

            // whlie(true)는 무한 루프를 돌 가능성이 높으니까
            for(let i=0; i<this.quizWordList.length; i++){
                const random = Math.floor(Math.random() * this.quizWordList.length)

                // 안전장치로 넣어두자.(같은 랜덤숫자가 많이 나올 경우.)
                if(randomList.includes(random)){
                    i--;
                    continue
                }

                randomList.push(random)
                const randomKor = this.quizWordList[random].kor

                if(!this.choiceKorList.includes(randomKor) && !randomKor.includes(questionWord.kor) && !questionWord.kor.includes(randomKor)){
                    console.log(questionWord.kor.includes(randomKor))
                    this.choiceKorList.push(randomKor)
                }

                if(this.choiceKorList.length==4){
                    this.choiceKorList = this.choiceKorList.sort(function(){
                    return 0.5 - Math.random()
                    })
                    break
                }
            }
            console.log(this.choiceKorList)
           
        },
        async initOption(){
            await this.initSequence()
            this.initTime()
            this.initWordList()
            this.setChoiceKorList(this.wordList[0])

        }
    },

    created(){
         this.initOption()
    },
    mounted(){
        // this.initOption()
    }
}
</script>

<style>
.quiz-container{
    display: flex;
    flex-direction: column;
    text-align: center;
}

</style>