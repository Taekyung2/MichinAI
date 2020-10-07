import Vue from 'vue'
import Vuex from 'vuex'

// import router from '@/router'
import axios from 'axios'

// api ?���? URL
// import SERVER from '@/api/drf'
import createPersistedState from 'vuex-persistedstate'
import Kakao from '@/store/modules/oauth/kakao.js'
import router from '../router'
import SERVER from '@/api/spring';

Vue.use(Vuex)

export default new Vuex.Store({
  // data?�� 집합
  state: {
    selectedWordbook: null,
    selectedChat: null,

    isSelectedQuizOption: false,
    isStartedQuiz: false,
    isResultQuiz: false,
    userBotKey: '',
    navigationTitle: 'Title',
    conversationList: [],
    loadingConversation: false,
    myWordbook: null,
    baseWordbook: null

  },

  // state�? (�?공해?��) �??��?�� ?��?��?��
  getters: {
    wordbook(state) {
      return state.selectedWordbook
    },
    chat(state) {
      return state.selectedChat
    },
    isSelectedQuizOption(state) {
      return state.isSelectedQuizOption
    },
    isStartedQuiz(state) {
      return state.isStartedQuiz
    },
    isResultQuiz(state) {
      return state.isResultQuiz
    },
    userBotKey(state) {
      return state.userBotKey
    },

    getConversationList(state) {
      return state.conversationList
    },
    getLoadingConversation(state){
      return state.loadingConversation
    },

    navigationTitle(state) {
      return state.navigationTitle
    },
    getmyWordbook(state) {
      return state.myWordbook
    },
    getbaseWordbook(state) {
      return state.baseWordbook

    }
  },

  // state�? �?경하?�� ?��?�� (commit?�� ?��?�� ?��?��)
  // ?��?�� ?��름으�? ?��?�� - 중요?��?�� ?��???��
  mutations: {
    SET_SELECTED_WORDBOOK(state, wordbook) {
      console.log(wordbook)
      state.selectedWordbook = wordbook
    },
    SET_SELECTED_CHAT(state, chat) {
      state.selectedChat = chat
    },
    SET_SELECTED_QUIZ_OPTION(state) {
      state.isSelectedQuizOption = !state.isSelectedQuizOption
    },
    SET_STARTED_QUIZ(state) {
      state.isStartedQuiz = !state.isStartedQuiz
    },
    SET_RESULT_QUIZ(state) {
      state.isResultQuiz = !state.isResultQuiz
    },
    SET_USERBOT_KEY(state, botKey) {
      state.userBotKey = botKey
    },
    INIT_QUIZ(state) {
      state.isSelectedQuizOption = true
      state.isStartedQuiz = false
      state.isResultQuiz = false
    },
    END_QUIZ(state) {
      state.isStartedQuiz = false
      state.isResultQuiz = false
    },

    SET_NAVIGATION_TITLE(state, title) {
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
      state.loadingConversation = true
    },
    LOADING_CONVERSATION(state){
      state.loadingConversation = false
    }
  },

  // 범용?��?�� ?��?��?�� (dispatch�? ?��?�� ?��?��)
  actions: {
    selectedWordbook({ commit }, payload) {
      commit('SET_SELECTED_WORDBOOK', payload.wordbook)
      router.push({
        name: 'WordbookDetail',
        params: { isBase: payload.chk }
      })
    },
    selectedChat({ commit }, chat) {
      commit('SET_SELECTED_CHAT', chat)
      router.push({ name: 'ChatDetail' })
    },
    //Wordbook Axios
    getWordbook({ commit }, userId) {
      axios.get(SERVER.URL + SERVER.ROUTES.getWordbook + userId)
        .then(res => {
          commit('SET_WORDBOOK', res)
        })
        .catch(err => {
          console.log('error' + err.response)
        });
    },
    getBaseWordbook({ commit }) {
      axios.get(SERVER.URL + SERVER.ROUTES.getBaseWordbook)
        .then(res => {
          commit('SET_BASEWORDBOOK', res)
        })
        .catch(err => {
          console.log('error' + err.response)
        })
    },
  },

  modules: {
    Kakao: Kakao
  },

  plugins: [
    createPersistedState({
      paths: [
        'Kakao',
      ],
    }),
  ]
})