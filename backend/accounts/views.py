from django.shortcuts import redirect
from rest_framework.response import Response
from rest_framework.decorators import api_view
from django.contrib.auth import login

import requests
from . import models
from .serializers import UserSerializer

import urllib

REST_API_KEY = "10c941be465bfb603fc1f9a9f00d147a"
MICHIN_DOMAIN = "http://127.0.0.1:8000"

class KakaoException(Exception):
    pass

# code 요청
def kakao_login(request):
    redirect_uri = MICHIN_DOMAIN + "/account/login/kakao/callback/"
    return redirect(
        f"https://kauth.kakao.com/oauth/authorize?client_id={REST_API_KEY}&redirect_uri={redirect_uri}&response_type=code"
    )

@api_view(['GET'])
def kakao_callback(req):
    try:
        redirect_uri = MICHIN_DOMAIN + "/account/login/kakao/callback/"
        user_code = req.GET.get("code")

        # access token 요청
        token_request = requests.get(
            f"https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id={REST_API_KEY}&redirect_uri={redirect_uri}&code={user_code}"
        )

        token_response_json = token_request.json()
        error = token_response_json.get("error", None)

        if error is not None:
            raise KakaoException()

        access_token = token_response_json.get("access_token")

        # 회원정보 요청
        profile_request = requests.post(
            "https://kapi.kakao.com/v2/user/me",
            headers={"Authorization": f"Bearer {access_token}"},
        )
        profile_json = profile_request.json()

        user_id = profile_json.get("id")
        kakao_account = profile_json.get("kakao_account")
        email = kakao_account.get("email", None)
        # if email is None:
        #     raise KakaoException()

        profile = kakao_account.get("profile")
        nickname = profile.get("nickname")
        profile_img = profile.get("thumnail_img_url")

        try:
            user_in_db = models.User.objects.get(username=user_id)
            if user_in_db:
                login(req, user_in_db, backend="django.contrib.auth.backends.ModelBackend")
                user = models.User.objects.get(username=user_id)
                serializer = UserSerializer(user)
                return Response(serializer.data)

        except models.User.DoesNotExist:
            new_user_to_db = models.User.objects.create(
                username = user_id,
                email = email,
                nickname = nickname,
            )

            new_user_to_db.set_unusable_password()
            new_user_to_db.save()
            login(
                req,
                new_user_to_db,
                backend="django.contrib.auth.backends.ModelBackend"
            )

            user = models.User.objects.get(username=user_id)
            serializer = UserSerializer(user)
            return Response(serializer.data)

    except KakaoException:
        return Response(data='error')




    # params = urllib.parse.urlencode(request.GET)
    # return redirect(f'http://127.0.0.1:8000/account/login/kakao/callback?{params}')