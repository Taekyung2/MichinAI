from klein import run, route
import json
from parlai.for_test.interactive_app.create_interactive import Interactive


@route("/bot/interact", methods=['POST'])
def interact(request):
    body = json.loads(request.content.read())
    return json.dumps(world.parley(body['u_id'], body['msg']), indent=4)


world = Interactive.main(
    model_file='zoo:blender/blender_90M/model',
    single_turn=True
)

run('localhost', 8999)