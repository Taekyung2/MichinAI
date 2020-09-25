from django.core.validators import MinValueValidator, MaxValueValidator
from djongo import models
# from django.db import models
from django.conf import settings


# Create your models here.
class Wordbook(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=100)
    user_id = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)

    # class Meta:
    #     app_label = "mongo"


class Word(models.Model):
    id = models.AutoField(primary_key=True)
    eng = models.CharField(max_length=200)
    kor = models.CharField(max_length=200)
    wordbook_id = models.ForeignKey(Wordbook, on_delete=models.CASCADE)

    # class Meta:
    #     app_label = "mongo"


class BaseWordBook(models.Model):
    id = models.AutoField(primary_key=True)
    level = models.IntegerField(validators=[MinValueValidator(0), MaxValueValidator(5)])
    eng = models.CharField(max_length=200)
    kor = models.CharField(max_length=200)

    # class Meta:
    #     app_label = "mongo"

