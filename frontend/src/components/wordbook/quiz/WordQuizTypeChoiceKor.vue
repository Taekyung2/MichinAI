<template>
    <div class="choice-container">
        <v-btn 
            v-for="(choiceKor, index) in choiceKorList" 
            :key="index"
            class="choice-btn"
            @click="submitUserAnswer(choiceKor)"
            v-text="choiceKor"
            :disabled="isClickWord==true"
        >
        </v-btn>
    </div>
</template>

<script>
export default {
    name: 'WordQuizTypeChoiceKor',
    props:{
        choiceKorList: Array,
        quizAnswer: Object,
    },
    data(){
        return{
            isClickWord: false,
        }
    },
    methods:{
        submitUserAnswer(choiceKor){
            this.isClickWord = true
            if(this.quizAnswer.kor == choiceKor){
                this.$emit('next-quiz',true)
                event.target.classList.add('answer-word')
                console.log('정답이다ㅏㅏ')
            }
            else{
                this.$emit('next-quiz',false)
                event.target.classList.add('wrong-word')
                console.log('틀렸다ㅏ아ㅏ! 뭉충아아ㅏㅏㄴ')
            }
            const eventClass = event.target.classList
            setTimeout(
                function(){
                    eventClass.remove('answer-word')
                    eventClass.remove('wrong-word')
                    console.log(this.isClickWord)
               }
            ,2500)

        }
    },
}
</script>

<style>
.choice-container{
    display: flex;
    flex-direction: column;
}
.choice-btn{
    margin: 5px 0;
}
.answer-word{
    background: #B2FF59 !important;
}
.wrong-word{
    background: #f85369 !important;
}
.disable-events {
  pointer-events: none
}
</style>