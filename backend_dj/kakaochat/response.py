from types import SimpleNamespace


class Link:
    def __init__(self, pc: str = None, mobile: str = None, web: str = None):
        self.pc = pc
        self.mobile = mobile
        self.web = web

    def __getitem__(self, key):
        return getattr(self, key)


class Thumbnail:
    def __init__(self, imageUrl: str, link: Link = None):
        self.imageUrl = imageUrl
        self.link = link

    def __getitem__(self, key):
        return getattr(self, key)


class Button:
    def webLink(self, label: str, webLinkUrl: str):
        self.label = label
        self.action = "webLink"
        self.webLinkUrl = webLinkUrl

    def message(self, label: str, messageText: str):
        self.label = label
        self.action = "message"
        self.messageText = messageText

    def block(self, label: str, messageText: str, blockId: str):
        self.label = label
        self.action = "block"
        self.messageText = messageText
        self.blockId = blockId

    # def __init__(self, label: str=None, action: str=None, webLinkUrl: str = None, messageText: str = None,
    #              phoneNumber: str = None,
    #              blockId: str = None,
    #              extra: dict = None):
    #     self.label = label
    #     self.action = action
    #     self.webLinkUrl = webLinkUrl
    #     self.messageText = messageText
    #     self.phoneNumber = phoneNumber
    #     self.blockId = blockId
    #     self.extra = extra

    def __getitem__(self, key):
        return getattr(self, key)


class CarouselHeader:
    def __init__(self, title: str, description: str, thumbnail: Thumbnail):
        self.title = title
        self.description = description
        self.thumbnail = thumbnail

    def __getitem__(self, key):
        return getattr(self, key)


class SimpleText:
    def __init__(self, text: str):
        self.text = text

    def __getitem__(self, key):
        return getattr(self, key)


class SimpleImage:
    def __init__(self, imageUrl: str, altText: str):
        self.imageUrl = imageUrl
        self.altText = altText

    def __getitem__(self, key):
        return getattr(self, key)


class BasicCard:
    def __init__(self, thumbnail: Thumbnail, title: str = None, description: str = None, buttons: list = None):
        self.thumbnail = thumbnail
        self.title = title
        self.description = description
        self.buttons = buttons

    def __getitem__(self, key):
        return getattr(self, key)


class ListItem:
    def __init__(self, title: str, description: str = None, imageUrl: str = None, link: Link = None):
        self.title = title
        self.description = description
        self.imageUrl = imageUrl
        if isinstance(link, Link):
            self.link = link

    def __getitem__(self, key):
        return getattr(self, key)


class ListCard:
    def __init__(self, header: ListItem, items: list, buttons: list = None):
        self.header = header
        self.items = items
        self.buttons = buttons

    def __getitem__(self, key):
        return getattr(self, key)


class Carousel:
    def __init__(self, type: str, items: list, headers: list = None):
        self.type = type
        self.items = items
        self.headers = headers

    def __getitem__(self, key):
        return getattr(self, key)


class QuickReply:
    def action(self, label: str, messageText: str, extra=None):
        self.__init__(label, "action", messageText, extra)

    def message(self, label: str, messageText: str, extra=None):
        self.__init__(label, "message", messageText, extra)

    def __init__(self, label: str, action: str, messageText: str, blockId: str = None, extra=None):
        self.label = label
        self.action = action
        self.messageText = messageText
        self.blockId = blockId
        self.extra = extra

    def __getitem__(self, key):
        return getattr(self, key)


class SkillTemplate:

    def __init__(self):
        self.version = "2.0"
        self.template = {'outputs': list(), 'quickReplies': list()}

    def addSimpleText(self, simpleText: SimpleText):
        self.template['outputs'].append({"simpleText": simpleText})
        return self

    def addSimpleImage(self, simpleImage: SimpleImage):
        self.template['outputs'].append({"simpleImage": simpleImage})
        return self

    def addBasicCard(self, basicCard: BasicCard):
        self.template['outputs'].append({"basicCard": basicCard})
        return self

    def addListCard(self, listCard: ListCard):
        self.template['outputs'].append({"listCard": listCard})
        return self

    def addQuickReply(self, quickReply: QuickReply):
        self.template['quickReplies'].append({"quickReply": quickReply})
        return self

    def __getitem__(self, key):
        return getattr(self, key)


def obj_to_dict(obj):
    res = {}
    if isinstance(obj, str) or isinstance(obj, int) or obj == None:
        return obj

    if type(obj) is dict:
        for k, v in obj.items():
            res[k] = obj_to_dict(v)
        return res

    if type(obj) is list:
        return [obj_to_dict(l) for l in obj]

    for k in obj.__dict__.keys():
        res[k] = obj_to_dict(obj[k])

    return res
