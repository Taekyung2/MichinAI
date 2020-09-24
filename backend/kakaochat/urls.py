from  django.conf.urls import url
from kakaochat import views

urlpatterns = [
    url('chat/start',views.chat_start),
    url('chat', views.chat),
    url('chat/end',views.chat_end),
    url('conversation', views.conversation),
    url('wordbook', views.wordbook_list),
    url('wordbook/<wordbook>',views.word_list),
    url('connect_check',views.connect_check)
]