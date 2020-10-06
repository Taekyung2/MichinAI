import Vue from 'vue'
import Vuex from 'vuex'

// import router from '@/router'
import axios from 'axios'

// api ?μ²? URL
// import SERVER from '@/api/drf'
import createPersistedState from 'vuex-persistedstate'
import Kakao from '@/store/modules/oauth/kakao.js'
import router from '../router'
import SERVER from '@/api/spring';

Vue.use(Vuex)

export default new Vuex.Store({
  // data? μ§ν©
  state: {
    selectedWordbook: null,
    selectedChat: null,

    isSelectedQuizOption: false,
    isStartedQuiz: false,
    isResultQuiz: false,
    userBotKey: '',
    navigationTitle: 'Title',
    conversationList: [],
    myWordbook : null,
    baseWordbook : null

  },
  
  // stateλ₯? (κ°?κ³΅ν΄?) κ°?? Έ?¬ ?¨??€
  getters: {
    wordbook(state){
      return state.selectedWordbook
    },
    chat(state){
      return state.selectedChat
    },
    isSelectedQuizOption(state){
      return state.isSelectedQuizOption
    },
    isStartedQuiz(state){
      return state.isStartedQuiz
    },
    isResultQuiz(state){
      return state.isResultQuiz
    },
    userBotKey(state){
      return state.userBotKey
    },
    
    getConversationList(state) {
      return state.conversationList
    },

    navigationTitle(state){
      return state.navigationTitle
    },
    getmyWordbook(state) {
      return state.myWordbook
    },
    getbaseWordbook(state) {
      return state.baseWordbook

    }
  },

  // stateλ₯? λ³?κ²½ν? ?¨? (commit? ?΅?΄ ?€?)
  // ?? ?΄λ¦μΌλ‘? ??± - μ€μ?¨? ????
  mutations: {
    SET_SELECTED_WORDBOOK(state, wordbook){
      state.selectedWordbook = wordbook
    },
    SET_SELECTED_CHAT(state, chat){
      state.selectedChat = chat
    },
    SET_SELECTED_QUIZ_OPTION(state){
      state.isSelectedQuizOption = !state.isSelectedQuizOption
    },
    SET_STARTED_QUIZ(state){
      state.isStartedQuiz = !state.isStartedQuiz
    },
    SET_RESULT_QUIZ(state){
      state.isResultQuiz = !state.isResultQuiz
    },
    SET_USERBOT_KEY(state, botKey){
      state.userBotKey = botKey
    },
    INIT_QUIZ(state){
      state.isSelectedQuizOption = true
      state.isStartedQuiz = false
      state.isResultQuiz = false
    },
    END_QUIZ(state){
      state.isStartedQuiz = false
      state.isResultQuiz = false
    },

    SET_NAVIGATION_TITLE(state, title){
      state.navigationTitle = title
    },
    SET_WORDBOOK(state, val) {
      state.myWordbook = val.data.wordbooks
    },
    SET_BASEWORDBOOK(state, val) {
      state.baseWordbook = val.data.wordbooks
    },
    SET_CONVERSATIONLIST(state, conv) {
      state.conversationList = conv
    },
  },

  // λ²μ©? ?Έ ?¨??€ (dispatchλ₯? ?΅?΄ ?€?)
  actions: {
    selectedWordbook({commit}, wordbook){
      commit('SET_SELECTED_WORDBOOK', wordbook)
      router.push({name:'WordbookDetail'})
    },
    selectedChat({commit}, chat){
      commit('SET_SELECTED_CHAT', chat)
      router.push({name: 'ChatDetail'})
    },
    //Wordbook Axios
    getWordbook({commit}, userId) {
      axios.get(SERVER.URL + SERVER.ROUTES.getWordbook + userId)
        .then(res => {
          commit('SET_WORDBOOK', res)
        })
        .catch(err => {
          console.log('error'+ err.response)
        });
    },
    getBaseWordbook({commit}) {
      axios.get(SERVER.URL + SERVER.ROUTES.getBaseWordbook)
        .then(res => {
          commit('SET_BASEWORDBOOK', res)
        })
        .catch(err => {
          console.log('error' + err.response)
        })
    }
  },

  modules: {
    Kakao : Kakao
  },

  plugins: [
    createPersistedState({
      paths: [
        'Kakao',
      ],
    }),
  ]
})