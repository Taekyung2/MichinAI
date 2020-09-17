
const User = {
    userName: '',
    userId: '',
    profileImg: '',
    accessToken: ''
}
const Kakao = {
    namespaced: true,
    state: {
        account: User,
        test : false
    },
    getters: {
        isLoggedIn: state => !!state.account.accessToken
    },

    mutations: {
        SET_KAKAO_AUTH(state, authUser){
            state.account = authUser
        },
        SUCCESS_LOGOUT(state){
            state.account = User
        }
    },
    actions: {
        init() {
            window.Kakao.init(process.env.VUE_APP_KAKAO_API_KEY)
        },
        getAuthUserInfo({ commit },authObj){
            window.Kakao.API.request({
                url:'/v2/user/me',
                success: res => {
                    const kakao = res.kakao_account
                    const authUser = {
                        userName: kakao.profile.nickname,
                        userId: kakao.email,
                        profileImg: kakao.profile.profile_image_url,
                        accessToken: authObj.access_token
                    }
                    commit('SET_KAKAO_AUTH', authUser)
                },
                fail: error => console.log(error)
                
            })
        },
        login({dispatch}){
            // console.log(window.Kakao)
            window.Kakao.Auth.loginForm({
                scope : 'account_email, profile',
                success: res => dispatch('getAuthUserInfo',res),
                fail: error => console.log(error)
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
        },

    },
}

export default Kakao