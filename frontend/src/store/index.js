import Vue from 'vue'
import Vuex from 'vuex'

// import router from '@/router'
// import axios from 'axios'

// api 요청 URL
// import SERVER from '@/api/drf'
import createPersistedState from 'vuex-persistedstate'
import Kakao from '@/store/modules/oauth/kakao.js'
import router from '../router'

Vue.use(Vuex)

export default new Vuex.Store({
  // data의 집합
  state: {
    selectedWordbook: null,
    selectedChat: null,

    isSelectedQuizOption: false,
    isStartedQuiz: false,
    isResultQuiz: false,
    userBotKey: '',
    navigationTitle: 'Title',

  },
  
  // state를 (가공해서) 가져올 함수들
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
    navigationTitle(state){
      return state.navigationTitle
    }
  },

  // state를 변경하는 함수 (commit을 통해 실행)
  // 상수 이름으로 작성 - 중요함을 나타냄
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
    }
  },

  // 범용적인 함수들 (dispatch를 통해 실행)
  actions: {
    selectedWordbook({commit}, wordbook){
      commit('SET_SELECTED_WORDBOOK', wordbook)
      router.push({name:'WordbookDetail'})
    },
    selectedChat({commit}, chat){
      commit('SET_SELECTED_CHAT', chat)
      router.push({name: 'ChatDetail'})
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
