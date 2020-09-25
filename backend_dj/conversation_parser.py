import requests
from bs4 import BeautifulSoup
import json
from datetime import datetime
from selenium import webdriver

import os


# os.environ.setdefault("DJANGO_SETTINGS_MODULE","backend.settings")

# import django
# django.setup()

# from conversation.models import Conversation

def saveConversation(date):
    if date is None:
        date = datetime.now().strftime('%Y%m%d')
    driver = webdriver.Chrome('c:/Users/multicampus/Desktop/chromedriver')
    driver.implicitly_wait(10)
    # driver.get("https://learn.dict.naver.com/conversation#/endic/" + date)
    driver.get("https://quizlet.com/35123390/%EC%A7%A7%EC%9D%80-%EC%8B%A4%EC%9A%A9-%EC%98%81%EC%96%B4-%EB%AC%B8%EC%9E%A5-1000%EA%B0%9C-%EC%98%81%EC%9E%91%EB%AC%B8-%EA%B8%B0%EC%B4%88-flash-cards/")

    html = driver.page_source
    soup = BeautifulSoup(html, 'html.parser')

    eng = soup.select('.lang-en')
    kor = soup.select('.lang-ko')

    for k,e in zip(eng, kor):
        print(k.get_text(), e.get_text())


saveConversation("20200921");
