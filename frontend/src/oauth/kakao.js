const Kakao = {
    Init() {
        window.Kakao.init(process.env.VUE_APP_KAKAO_API_KEY)
        console.log('kakao : ' + window.Kakao.isInitialized());
    },
    
    GetMe(authObj){
        window.Kakao.API.request({
            url:'/v2/user/me',
            success: res => {
                console.log(res.kakao_account)
                // const kakao_account = res.kakao_account
                window.Kakao.Auth.setAccessToken(authObj.access_token)
            },
            fail: error => {
                console.log(error)
            }
        })
    },
    Login(){
        console.log(window.Kakao)
        window.Kakao.Auth.loginForm({
            scope : 'account_email, profile',
            success: this.GetMe,
            fail: error => {
                console.log(error)
            }
        })
    },
    Logout(){
        if (!window.Kakao.Auth.getAccessToken()) {
            console.log('Not logged in.')
            return
        }
        
        window.Kakao.Auth.logout(function(){
            console.log(window.Kakao.Auth.getAccessToken())
        })
        
    }
}
export default Kakao;