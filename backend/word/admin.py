from django.contrib import admin
from .models import Word, Wordbook, BaseWordBook

# Register your models here.
admin.site.register(Word)
admin.site.register(Wordbook)
admin.site.register(BaseWordBook)