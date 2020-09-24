from django.shortcuts import render
from django.http.response import JsonResponse
from requests import Response
from rest_framework.parsers import JSONParser
from rest_framework import status

from rest_framework.decorators import api_view

from kakaochat.response import *
import json

from rest_framework.status import HTTP_200_OK
connectResponse = SkillTemplate()
btn = Button()
btn.message("이메일 연동하기", "연동하기")
thumb = Thumbnail("https://imgnews.pstatic.net/image/030/2016/06/23/814878_20160623155923_025_0001_99_20160623180322.jpg")
connectResponse.addBasicCard(BasicCard(thumb, "이메일 연동하기", "등록되지 않은 사용자입니다. 이메일을 연동해주세요!", [btn]))

@api_view(['POST'])
def chat_start(request):
    req = JSONParser().parse(request)
    if connection_check(req['userRequest']['user']['id']):
        pass
    else:
        return JsonResponse(obj_to_dict(connectResponse))


@api_view(['POST'])
def chat(request):
    req = JSONParser().parse(request)
    print(req)
    if len(req['contexts']) == 0 or len(req['contexts'][0]['params'])==0:
        print("폴백")
    else:
        contexts = req['contexts'][0]['name']
        print(contexts)

        if contexts == 'chat_state':
            print("채팅 중입니다")
        elif contexts == 'connect':
            print("연동")


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



def connection_check(userBotId):
    # 희진이 호출!
    return False



