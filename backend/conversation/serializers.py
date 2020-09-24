from rest_framework import serializers
from conversation.models import Conversation


class ConversationSerializer(serializers.ModelSerializer):
    class Meta:
        model = Conversation
        fields = ('id',
                  'date',
                  'eng',
                  'kor')
