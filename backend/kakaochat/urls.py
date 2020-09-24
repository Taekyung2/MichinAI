from  django.conf.urls import url
from kakaochat import views

urlpatterns = [
    url('kakao/chat/start',views.chat_start),
    url('kakao/chat', views.chat),
    url('kakao/chat/end',views.chat_end),
    url('kakao/conversation', views.conversation),
    url('kakao/wordbook', views.wordbook_list),
    url('kakao/wordbook/<wordbook>',views.word_list)
]