import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
      path: '/conversation',
      name: 'Conversation',
      component: () => import('../views/Conversation.vue')
    },
    {
      path: '/chatbot',
      name: 'Chatbot',
      component: () => import('../views/Chatbot.vue')
    },
    {
      path: '/wordbook',
      name: 'Wordbook',
      component: () => import('../views/Wordbook.vue')
    },
    {
      path: '/score',
      name: 'Score',
      component: () => import('../views/Score.vue')
    },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// 페이지 검증
router.beforeEach((to, from, next) => {
  // 로그인이 필요 없는 Page
  const publicPages = ['/'] 

  const authRequired = !publicPages.includes(to.name) // Login 해야 함
  
  const isLoggedIn = !!window.Kakao.Auth.getAccessToken() // Login이 되어 있는지 

  if(authRequired && isLoggedIn) next() 
  
})

export default router
