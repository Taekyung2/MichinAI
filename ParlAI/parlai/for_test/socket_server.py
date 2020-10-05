import socket
import json
from pprint import pprint
from _thread import *
from multiprocessing import Process
from parlai.for_test.interactive_app.create_interactive import Interactive
from twisted.internet import reactor, protocol


def clone_world(base_world):
    new_world = base_world.clone()
    base_agents = base_world.get_agents()
    new_world.set_agents([base_agents[0].clone(), base_agents[1].clone()])

    return new_world


def threaded(client_socket, addr, world):
    print('Connected by : ', addr[0], ':', addr[1])
    # world = clone_world(base_world)
    while True:
        try:
            data = client_socket.recv(1024)

            if not data:
                print('Disconnted by ' + addr[0], ':', addr[1])
                break
            data = data.decode().split('\r\n')
            for d in data:
                if d == '': continue
                print(d)
                d = json.loads(d)
                print('Received from ' + addr[0], ':', addr[1], d)

                if d['command'] == 'start':
                    # clone_world(data['u_id'])
                    response = {
                        'text': 'talk to me anything!'
                    }
                elif d['command'] == 'interact':
                    response = world.parley('base', d['msg'])
                client_socket.send(json.dumps(response).encode())

        except ConnectionResetError as e:
            print('Disconnected by ' + addr[0], ':', addr[1])
            break

    client_socket.close()


if __name__ == '__main__':
    HOST = 'localhost'
    PORT = 8999

    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind((HOST, PORT))
    server_socket.listen()

    base_world = Interactive.main(
        model_file='zoo:blender/blender_90M/model',
        single_turn=True
    )

    print('Server Start [', HOST, ':', PORT, ']')

    while True:
        print('Wait...')

        client_socket, addr = server_socket.accept()
        world = clone_world(base_world)
        start_new_thread(threaded, (client_socket, addr, world))
        # procs = Process(target=threaded, args=(client_socket, addr, base_world))
        # procs.start()

    server_socket.close()
