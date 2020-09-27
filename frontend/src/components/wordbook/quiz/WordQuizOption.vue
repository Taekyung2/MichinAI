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
            <v-toolbar-title>퀴즈 설정</v-toolbar-title>
            <v-spacer></v-spacer>
        </v-toolbar>

        <div>
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>
                        <h5 class="option-title">퀴즈유형</h5>
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        <v-chip-group
                            v-model="selectedType"
                            column
                            mandatory 
                            :active-class="selectedTag"
                        >
                            <v-chip
                                filter
                                outlined
                                v-for="(quizType,index) in quizTypeList"
                                :key="index"
                                :value="quizType"
                            >
                            {{ quizType }}
                            </v-chip>
                        </v-chip-group>
                    </v-list-item-subtitle>
                    <v-divider></v-divider>
                </v-list-item-content>
            </v-list-item>

            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>
                        <h5 class="option-title">제한시간</h5>
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        <v-select
                        v-model="selectedTime"
                        :items="quizTimeList"
                        menu-props="auto"
                        label="제한시간"
                        hide-details
                        prepend-icon="mdi-alarm"
                        single-line
                        class="select-time"
                        ></v-select>
                    </v-list-item-subtitle>
                    <v-divider></v-divider>
                </v-list-item-content>
            </v-list-item>

            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>
                        <h5 class="option-title">문항수</h5>
                    </v-list-item-title>
                    <v-list-item-subtitle>
                    <v-chip-group
                        v-model="selectedCountName"
                        column
                        mandatory 
                        :active-class="selectedTag"
                    >
                        <v-chip
                            filter
                            outlined
                            v-for="(quizCount,index) in quizCountList"
                            :key="index"
                            :value="quizCount"
                        >
                        {{ quizCount }}
                        </v-chip>
                        
                    </v-chip-group>
                    </v-list-item-subtitle>
                    <div v-if="selectedCountName==quizCountList[2]">
                            <!-- <v-text-field
                                v-model="title"
                                :rules="rules"
                                label="원하시는 퀴즈 문항수를 작성해주세요."
                            ></v-text-field> -->
                     
                        <v-slider
                        v-model="selectedCount"
                        thumb-label
                        ticks
                        :max="wordList.length"
                        min="1"
                        :messages="selectedCount + ' / ' + wordList.length"
                        >
                            <template v-slot:prepend>
                                <v-icon
                                    class="count-change-btn"
                                    @click="countDecrement"
                                    color="var(--main-color)"
                                >
                                    mdi-minus
                                </v-icon>
                                </template>

                            <template v-slot:append>
                                <v-icon
                                    class="count-change-btn"
                                    @click="countIncrement"
                                    color="var(--main-color)"
                                >
                                    mdi-plus
                                </v-icon>
                            </template>
                        </v-slider>
                    </div>
                    <v-divider></v-divider>
                </v-list-item-content>
            </v-list-item>

            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title>
                        <h5 class="option-title">순서</h5>
                    </v-list-item-title>
                    <v-list-item-subtitle>
                        <v-chip-group
                            v-model="selectedSequence"
                            column
                            mandatory 
                            :active-class="selectedTag"
                        >
                            <v-chip
                                filter
                                outlined
                                v-for="(quizSequence,index) in quizSequenceList"
                                :key="index"
                                :value="quizSequence"
                            >
                            {{ quizSequence }}
                            </v-chip>
                        </v-chip-group>
                    </v-list-item-subtitle>
                    <v-divider></v-divider>
                </v-list-item-content>
            </v-list-item>
            <v-list-item>
                <v-list-item-content>
                    <v-btn
                        color="primary"
                        dark
                        @click="openQuiz"
                    >
                        START
                    </v-btn>
                </v-list-item-content>
            </v-list-item>
        </div>

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
    data(){
        return{
            selectedType:'',
            selectedTime:'제한없음',
            selectedSequence:'',
            selectedCountName: '',
            selectedCount: 1,

            quizTypeList: ['OX', '객관식'],
            quizTimeList: ['제한없음','3초','5초','10초','20초','30초'],
            quizCountList: ['전체','미암기 단어','사용자 정의'],
            quizSequenceList: ['순서대로','랜덤'],
            selectedTag: 'selected-tag',
        }
    },
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
        },
        countDecrement () {
            this.selectedCount--
        },
        countIncrement () {
            this.selectedCount++
        },

       
    }
}
</script>

<style>
.option-title{
    color: var(--font-sub-color);
}
.selected-tag{
    color: var(--main-point-color) !important
}
.select-time{
    padding-top: 0px;
    margin-top: 0px;
}
.v-messages__message{
    color: var(--main-color);
    float: right;
    font-size: 14px;
    margin-bottom: 10px;
    margin-right: 5px;
}
.count-change-btn{
    margin: 0 5px;
}
</style>