from rest_framework import serializers
from .models import Words, Wordbook

class WordbookListSerializer(serializers.ModelSerializer):
    class Meta:
        model = Wordbook
        field = ['id', 'name']

class WordbookDetailSerializer(serializers.ModelSerializer):
    class Meta:
        model = Words
        filed = '__all__'

