from  django.conf.urls import url
from conversation import views

urlpatterns = [
    url(r'api/conversation', views.conversation_list)
]