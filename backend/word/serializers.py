from rest_framework import serializers
from .models import Word, Wordbook, BaseWordBook


class WordbookListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Wordbook
        field = ['id', 'name']


class WordbookDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = Word
        field = '__all__'


class BaseWordbookSerializer(serializers.ModelSerializer):
    class Meta:
        model = BaseWordBook
        field = '__all__'
