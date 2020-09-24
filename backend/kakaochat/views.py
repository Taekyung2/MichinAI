from django.shortcuts import render
from django.http.response import JsonResponse
from requests import Response
from rest_framework.parsers import JSONParser
from rest_framework import status

from rest_framework.decorators import api_view

from kakaochat.response import *
from kakaochat.responseTemplate import *
import requests
import json

from django.conf import settings

BASE_URL = settings.BASE_URL
CHAT_URL = settings.CHAT_URL


@api_view(['POST','GET'])
def chat_start(request):
    req = JSONParser().parse(request)
    if connection_check(req['userRequest']['user']['id']):
        res = requests.post(CHAT_URL + "/bot/start", "")
        print(res)
        pass
    else:
        res = ConnectBlock(req['userRequest']['user']['id'])
        return JsonResponse(obj_to_dict(res))


@api_view(['POST'])
def chat(request):
    req = JSONParser().parse(request)
    if len(req['contexts']) == 0 or len(req['contexts'][0]['params'])==0:
        res =PollBackCarousel()
        return JsonResponse(obj_to_dict(res))
    else:
        contexts = req['contexts'][0]['name']

        if contexts == 'chat_state':
            utterance = req['userRequest']['utterance']
            res = requests.post(CHAT_URL + "/bot/interact", utterance)
            print(res)

    res = SkillTemplate()
    res.addSimpleImage(SimpleImage("https://i.pinimg.com/originals/d9/82/f4/d982f4ec7d06f6910539472634e1f9b1.png","이미지 실패"))
    return JsonResponse(obj_to_dict(res))



@api_view(['POST'])
def chat_end(request):
    req = JSONParser().parse(request)


@api_view(['POST'])
def conversation(request):
    req = JSONParser().parse(request)


@api_view(['POST'])
def wordbook_list(request):
    req = JSONParser().parse(request)
    if connection_check(req['userRequest']['user']['id']):
        pass
    else:
        return JsonResponse(SimpleText("연동이완료되지 않았습니다! 연동을 진행해주세요"))

@api_view(['POST'])
def word_list(request):
    req = JSONParser().parse(request)
    if connection_check(req['userRequest']['user']['id']):
        pass
    else:
        return JsonResponse(SimpleText("연동이완료되지 않았습니다! 연동을 진행해주세요"))

@api_view(['POST'])
def connect_check(request):
    req = JSONParser().parse(request)
    if connection_check(req['userRequest']['user']['id']):
        pass
    else:
        res = ConnectionButton(req['userRequest']['user']['id'])
        print(obj_to_dict(res.toResponse()))
        return JsonResponse(obj_to_dict(res.toResponse()))



def connection_check(userBotId):
    # 희진이 호출!
    return False



