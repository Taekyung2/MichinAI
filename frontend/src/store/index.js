import Vue from 'vue'
import Vuex from 'vuex'

// import router from '@/router'
// import axios from 'axios'

// api 요청 URL
// import SERVER from '@/api/drf'

Vue.use(Vuex)

export default new Vuex.Store({
  // data의 집합
  state: {
    // local storage의 Authorization
    authToken: null
  },
  
  // state를 (가공해서) 가져올 함수들
  getters: {
    // 로그인 상태
    isLoggedIn: state => !!state.authToken,

  },

  // state를 변경하는 함수 (commit을 통해 실행)
  // 상수 이름으로 작성 - 중요함을 나타냄
  mutations: {

  },

  // 범용적인 함수들 (dispatch를 통해 실행)
  actions: {
  },

  modules: {
  }
})
