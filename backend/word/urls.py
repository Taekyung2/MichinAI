from django.urls import path
from . import views

app_name = 'word'

urlpatterns = [
    path('list/<int:userid>/', views.wordbook_list),
    path('<int:wordbookid>/', views.word_list),
]
