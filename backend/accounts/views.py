from django.shortcuts import redirect
import urllib

# Create your views here.
REST_API_KEY = "10c941be465bfb603fc1f9a9f00d147a"
KAKAO_DOMAIN = "https://kauth.kakao.com"
MICHIN_DOMAIN = "http://127.0.0.1:8000"

# code 요청
def kakao_login(request):
    redirect_uri = MICHIN_DOMAIN + "/account/login/kakao/callback/"
    return redirect(
        f"https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={redirect_uri}&response_type=code"
    )

# access token 요청
def kakao_callback(request):
    try:
        redirect_uri = MICHIN_DOMAIN + "/account/login/kakao/callback/"
        user_code = request.GET.get("code")

        token_request = request.get(
            f"https://kakao.com/oauth/token?grant_type=authorization_code"
            f"&client_id={REST_API_KEY}&redirect_uri={redirect_uri}&code={user_code}"
        )
        token_response_json = token_request.json()
        error = token_response_json.get("error", None)

        if error is not None:
            # raise KakaoException()
        access_token = token_response_json.get("access_token")

        profile_request = request.post(
            "https://kapi.kakao.com/v2/user/me",
            headers={"Authorizationi": f"Bearer {access_token}"},
        )
        profile_json = profile_request.json()

        # kakao_account = profi

    params = urllib.parse.urlencode(request.GET)
    return redirect(f'http://127.0.0.1:8000/account/login/kakao/callback?{params}')