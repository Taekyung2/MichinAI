import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import Kakao from '@/store/modules/oauth/kakao.js'

Vue.config.productionTip = false
Kakao.actions.init()

new Vue({
  store,
  router,
  render: h => h(App)
}).$mount('#app')
