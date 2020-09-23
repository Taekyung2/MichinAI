from django.urls import path
from . import views

app_name = 'accounts'

urlpatterns = [
    path('account/login/kakao', views.kakao_login),
    path('account/login/kakao/callback/', views.kakao_callback),
]
