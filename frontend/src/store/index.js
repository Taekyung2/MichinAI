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
    selectedQuizAll: false,
  },
  
  // state를 (가공해서) 가져올 함수들
  getters: {
    wordbook(state){
      return state.selectedWordbook
    },
    chat(state){
      return state.selectedChat
    },
    quizAll(state){
      console.log(state.selectedQuizAll )
      return state.selectedQuizAll
    }
  },

  // state를 변경하는 함수 (commit을 통해 실행)
  // 상수 이름으로 작성 - 중요함을 나타냄
  mutations: {
    SET_SELECTED_WORDBOOK(state, wordbook){
      console.log(wordbook.id)
      state.selectedWordbook = wordbook
    },
    SET_SELECTED_CHAT(state, chat){
      console.log(chat.id)
      state.selectedChat = chat
    },
    SET_SELECTED_QUIZ_ALL(state){
      console.log('quiz-all')
      state.selectedQuizAll = !state.selectedQuizAll
    }
  },

  // 범용적인 함수들 (dispatch를 통해 실행)
  actions: {
    selectedWordbook({commit}, wordbook){
      console.log('action - wordbook')
      commit('SET_SELECTED_WORDBOOK', wordbook)
      router.push({name:'WordbookDetail'})
    },
    selectedChat({commit}, chat){
      console.log('action - chat')
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
