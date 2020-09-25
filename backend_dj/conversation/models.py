from django.db import models


# Create your models here.
class Conversation(models.Model):
    date = models.CharField(max_length=20, null=False, blank=False)
    eng = models.CharField(max_length=200, null=False, blank=False)
    kor = models.CharField(max_length=200, null=False, blank=False)
