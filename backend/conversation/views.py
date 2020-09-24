from typing import re

from django.shortcuts import render
from django.http.response import JsonResponse
from rest_framework.parsers import JSONParser
from rest_framework import status

from conversation.models import Conversation
from conversation.serializers import ConversationSerializer
from rest_framework.decorators import api_view

from datetime import datetime

# Create your views here.
@api_view(['GET','POST'])
def conversation_list(request):
    print(JSONParser().parse(request))
    date = datetime.now().strftime('%Y%m%d')
    conversation = Conversation.objects.filter(date=date)
    print(conversation)

    conversation_serializer = ConversationSerializer(conversation, many=True)
    return JsonResponse(conversation_serializer.data, safe=False)



