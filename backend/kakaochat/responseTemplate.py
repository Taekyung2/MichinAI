from kakaochat.response import *
from django.conf import settings

BASE_URL = settings.BASE_URL

class ConnectBlock(SkillTemplate):
    def __init__(self, userBotKey: str):
        SkillTemplate.__init__(self)
        btn = ConnectionButton(userBotKey)
        thumb = Thumbnail(
            "https://i.ibb.co/SB9Y1Yw/Kakao-Talk-20200919-194442791.png")
        self.addBasicCard(BasicCard(thumb, "카카오톡 연동하기", "등록되지 않은 사용자입니다. 미친AI에 사용자 이메일을 연결해주세요!", [btn]))


class ConnectionButton(Button):
    def __init__(self, userBotKey: str):
        Button.__init__(self)
        self.webLink("이메일 연동하기", BASE_URL + "/connect?userBotKey=" + userBotKey)

    def toResponse(self):
        res = SkillTemplate()
        res.addBasicCard(BasicCard(None, "카카오톡 연동하기", "미친AI에 이메일을 연결해주세요!", [self]))
        return res

class PollBackCarousel(SkillTemplate):
    def __init__(self):
        SkillTemplate.__init__(self)
        thumb = Thumbnail(
            "https://i.ibb.co/5KDT33y/image.png")
        self.addBasicCard(BasicCard(thumb, "잘못된 입력입니다", "모라는지 하나두 모르겟쪙 ☹", []))