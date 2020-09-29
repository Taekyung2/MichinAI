from http.server import HTTPServer
from parlai.for_test.interactive_app.create_interactive import Interactive
from parlai.for_test.interactive_app.interact_request_handler import InteractRequestHandler
from klein import run, route
import json


@route("/bot/interact", methods=['POST'])
def bot_start(request):
    content = json.loads(request.content.read())

    u_id = content['u_id']
    text = content['msg']

    return json.dumps(world.parley(u_id, text), indent=4)


world = Interactive.main(
    model_file='zoo:blender/blender_90M/model',
)

run('localhost', 8999)



# if __name__ == '__main__':
#     # app = InteractRequestHandler(Interactive.main(
#     #     model_file='zoo:blender/blender_90M/model'
#     # ))
#     host = 'localhost'
#     port = 8080
#
#     httpd = HTTPServer((host, port), InteractRequestHandler)
#
#     print('Connect to the link : http://{}:{}'.format(host, port))
#
#     httpd.serve_forever()
