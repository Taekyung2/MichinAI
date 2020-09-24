<template>
    <v-dialog v-model="isSelectedQuizAll" fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card>
    <v-toolbar dark color="primary">
        <v-btn icon dark @click="SET_SELECTED_QUIZ_ALL">
        <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title>퀴즈</v-toolbar-title>
        <v-spacer></v-spacer>
    </v-toolbar>
    <v-list three-line subheader>
       
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
        selectAll : Boolean,
        wordList : Array,
    },
    computed: {
      ...mapGetters([
        'isSelectedQuizAll',
      ]),
    },
    methods: {
        ...mapMutations([
            'SET_SELECTED_QUIZ_ALL',
        ]),
       

    }
}
</script>

<style>

</style>