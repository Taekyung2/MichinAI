from django.shortcuts import render

from rest_framework.response import Response
from rest_framework.decorators import api_view

from .serializers import WordbookListSerializer, WordbookDetailSerializer
from .models import Wordbook, Word


@api_view(['GET'])
def wordbook_list(reqeust, userid):
    wordbooks = Wordbook.objects.filter(user_id=userid)
    serializer = WordbookListSerializer(wordbooks, many=True)
    return Response(serializer.data)


@api_view(['GET'])
def word_list(request, wordbookid):
    words = Word.object.filter(wordbook_id=wordbookid)
    serializer = WordbookDetailSerializer(words, many=True)
    return Response(serializer.data)
