from klein import Klein
import json
import time
from parlai.for_test.interactive_app.create_interactive import Interactive
from twisted.web.server import Site
from twisted.internet import threads
from twisted.internet import reactor, endpoints
from pprint import pprint
from copy import deepcopy


app = Klein()


def test(body):
    return json.dumps(world[body['u_id']].parley('base', body['msg']), indent=4)

    # reply = world.parley(body['u_id'], body['msg'])
    # print(reply)
    # return json.dumps(reply, indent=4)

    # return json.dumps(world.parley(body['u_id'], body['msg']), indent=4)


@app.route("/bot/interact", methods=['POST'])
def interact(request):
    body = json.loads(request.content.read())
    return threads.deferToThread(test, deepcopy(body))


@app.route("/bot/start", methods=['POST'])
def bot_start(request):
    body = json.loads(request.content.read())
    world[body['u_id']] = world['base'].clone()
    world[body['u_id']].set_agents([base_agents[0].clone(), base_agents[1].clone()])
    # world.new_user(body['u_id'])
    pprint(world)
    return body['u_id']


world = {}

world['base'] = Interactive.main(
# world = Interactive.main(
    model_file='zoo:blender/blender_90M/model',
    # single_turn=True,
)

base_agents = world['base'].get_agents()

reactor.suggestThreadPoolSize(20)

# this way reactor logs only print calls
web_server = endpoints.serverFromString(reactor, "tcp:8999")
web_server.listen(Site(app.resource()))
reactor.run()

# python ../../second_pjt/pythonProject/main.py