from django.db import models
from django.contrib.auth.models import AbstractUser

# Create your models here.
class User(AbstractUser):
    first_name = None
    last_name = None
    email = models.EmailField(null=True)
    nickname = models.CharField(max_length=50)
    botKey = models.CharField(max_length=100)
    exp = models.IntegerField(default=1)

    REQUIRED_FIELDS = []
    # objects = UserManager()

