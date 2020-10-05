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
        isMobileConnection: false,
        isMobileConnected: {
            message: '',
            state: true,
        },
    },
    getters: {
        isLoggedIn: state => !!state.account.accessToken,
        isMobileConnection: state => state.isMobileConnection,
        
        isSuccessMobileConnection: state => !!state.isMobileConnected.message,
        isMobileConnected: state => state.isMobileConnected,
    },

    mutations: {
        SET_KAKAO_AUTH(state, authUser){
            state.account = authUser
        },
        SET_MOBILE_CONNECTION(state, userBotKey){
            state.isMobileConnection = !!userBotKey
        },
        SUCCESS_LOGOUT(state){
            state.account = User
        },
        SET_RESULT_CONNECTION(state, result){
            state.isMobileConnected = result
            console.log(state.isMobileConnected)
        },
        SET_MESSAGE_LOGIN(state, message){
            state.messageLogged = message
        }
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
                    commit('SET_MOBILE_CONNECTION','')
                    
                    axios.post(SERVER.URL + SERVER.ROUTES.login , authUser)
                    .then(res=>{
                        console.log(res)
                        const result={
                            message : '미친아이와 연동 되었습니다.',
                            state : true
                        }
                        commit('SET_RESULT_CONNECTION',result)
                    })
                    .catch(err => {
                        console.log(err)
                        const result={
                            message : '이미 미친아이와 연동된 아이디입니다.',
                            state : false
                        }
                        commit('SET_RESULT_CONNECTION',result)
                    })
                },
                fail: error => {
                    console.log(error)
                }
                
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