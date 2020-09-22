from rest_framework import serializers
from .models import Words, Wordbook

class WordSerializer(serializers.ModelSerializer):
    class Meta:
        model = Words
        field = ['id', 'i']