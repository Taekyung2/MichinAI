from rest_framework import serializers
from .models import Word, Wordbook


class WordbookListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Wordbook
        field = ['id', 'name']


class WordbookDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = Word
        filed = '__all__'