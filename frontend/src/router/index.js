import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
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
  // // 로그인이 필요 없는 Page
  // const publicPages = ['Login','Introduction'] // Login 안해도 됨
  // const authPages = ['Login'] // Login 되어있으면 안됨

  // const authRequired = !publicPages.includes(to.name) // Login 해야 함
  // const unauthRequired = authPages.includes(to.name) // Login 해서는 안됨
  // const isLoggedIn = true // Login이 되어 있는지 

  // if(unauthRequired && isLoggedIn){
  //   next('/') // 이동
  // }
  // authRequired && !isLoggedIn? next({name: 'Login'}) : next()
  
})

export default router
