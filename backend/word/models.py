from django.db import models
from django.conf import settings

# Create your models here.
class Wordbook(models.Model):
    name = models.CharField(max_length=100)
    user_id = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.PROTECT)
    level = models.IntegerField()

class Words(models.Model):
    wordbook_id = models.ForeignKey(Wordbook, on_delete=models.PROTECT)
    eng = models.CharField(max_length=200)
    kor = models.CharField(max_length=200)

class Default(models.Model):
    level = models.IntegerField()
    eng = models.CharField(max_length=200)
    kor = models.CharField(max_length=200)
