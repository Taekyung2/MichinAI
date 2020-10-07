<template>
  <div>
    <v-dialog
      v-model="isResultQuiz"
      width="500"
      persistent
    >
      <v-card>
        <v-card-title class="headline orange--text">
          RESULT
        </v-card-title>
        <v-card-title>
          <h1>{{ resultText() }}</h1>
        </v-card-title>
        <v-card-text class="layout justify-center">
          <v-progress-circular
            :rotate="-90"
            :size="100"
            :width="15"
            :value="value"
            color="#7db3d9"
          >
            {{ value }}
          </v-progress-circular>
        </v-card-text>
        <v-card-text>
        <div>맞은개수 : {{resultCount}} / 틀린개수 : {{resultWordList.length - resultCount}}</div>
        </v-card-text>
        <v-divider></v-divider>
        <!-- <v-card-text>
          <div v-for="(wordList,index) in resultWordList" :key="index">
            <li>{{wordList.eng}} | {{wordList.kor}} | {{ wordList.check?'O':'X'}}</li>
          </div>
        </v-card-text> -->
        <!-- <v-divider></v-divider> -->
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
            color="primary"
            text
            @click="END_QUIZ"
          >
            나가기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import { mapGetters, mapMutations } from 'vuex'
export default {
    name: 'WordQuizResult',
    computed: {
      ...mapGetters([
        'isResultQuiz',
      ]),
    },
    props:{
      resultCount: Number,
      resultWordList: Array,
    },
    methods: {
        ...mapMutations([
            'END_QUIZ',
        ]),
        resultText(){
          if(this.percentage>=90)
            return 'Perfect!'
          else if(this.percentage>=70)
            return 'Great'
          else if(this.percentage>=50)
            return 'Good '
          else
            return 'Try Again :('
        }
    },
    data () {
      return {
        interval: {},
        value: 0,
        percentage: (this.resultCount/this.resultWordList.length)*100,
      }
    },
    mounted () {
      this.interval = setInterval(() => {
        return this.value = ((this.resultCount/this.resultWordList.length)*100).toFixed(2)
      }, 1000)
    },
}
</script>

<style scoped>
.v-progress-circular {
  margin: 1rem;
}
</style>