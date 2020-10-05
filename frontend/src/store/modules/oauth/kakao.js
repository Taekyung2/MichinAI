import router from '@/router'
import axios from 'axios'
import SERVER from '@/api/spring.js'

const User = {
    userName: '',
    userId: 0,
    profileImg: '',
    accessToken: ''
}
const Kakao = {
    namespaced: true,
    state: {
        account: User,
        isMobileLogin: false,
    },
    getters: {
        isLoggedIn: state => !!state.account.accessToken,
        isMobileLogin: state => state.isMobileLogin,
        account: state => state.account
    },

    mutations: {
        SET_KAKAO_AUTH(state, authUser){
            state.account = authUser
        },
        SET_MOBILE_LOGIN(state, userBotKey){
            state.isMobileLogin = !!userBotKey
        },
        SUCCESS_LOGOUT(state){
            state.account = User
        },

    },
    actions: {
        init() {
            window.Kakao.init('ba9c2e1cc89511e843660f021588fb20')
            // window.Kakao.init(process.env.VUE_APP_KAKAO_API_KEY)
        },
        getAuthUserInfo({ commit },authObj){
            console.log(authObj)
            window.Kakao.API.request({
                url:'/v2/user/me',
                success: res => {
                    console.log(res.id)
                    const kakao = res.kakao_account
                    const authUser = {
                        userName: kakao.profile.nickname,
                        userId: res.id,
                        profileImg: kakao.profile.profile_image_url,
                        accessToken: authObj.access_token,
                        userBotKey: localStorage.getItem('userBotKey')
                    }
                    commit('SET_KAKAO_AUTH', authUser)
                    localStorage.setItem('userBotKey','')
                    
                    axios.post(SERVER.URL + SERVER.ROUTES.login , authUser)
                    .then(res=>{console.log(res)}) 
                    .catch(err => console.log(err))
                },
                fail: error => console.log(error)
                
            })
        },
        login({ dispatch }){
            window.Kakao.Auth.loginForm({
                scope : 'profile',
                success: res => dispatch('getAuthUserInfo',res),
                fail: err => console.log(err)
            })
        },

        logout({commit}){
            if (!window.Kakao.Auth.getAccessToken()) {
                console.log('Not logged in.')
                return
            }
            
            window.Kakao.Auth.logout(function(){
                commit('SUCCESS_LOGOUT')
                localStorage.clear();
            })
            router.push('/')
        },

    },
}

export default Kakao