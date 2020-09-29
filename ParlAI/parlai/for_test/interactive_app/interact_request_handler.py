import json
from http.server import BaseHTTPRequestHandler

from parlai.for_test.interactive_app.create_interactive import Interactive


class InteractRequestHandler(BaseHTTPRequestHandler):
    # 이거 class callable로 써서 attribute를 사용할 수 없음
    # 어케하징
    world: None

    def do_POST(self):
        if self.path == '/bot/start':
            self.world = Interactive.main(
                model_file='zoo:blender/blender_90M/model'
            )
            self._respond(self._set_response(200, 'Started', False))
        if self.path == '/bot/interact':
            pass

    def do_GET(self):
        if self.path == '/test':
            self._respond(self._set_response(200, str(self.world), False))

    def _set_response(self, status_code, text, episode_done):
        return {
            'status_code': status_code,
            'text': text,
            'episode_done': episode_done
        }

    def _set_header(self, status_code):
        self.send_response(status_code)
        self.send_header('Content-type', 'application/json')
        self.end_headers()

    def _respond(self, data):
        self._set_header(data['status_code'])
        json_str = json.dumps(data)
        self.wfile.write(bytes(json_str, 'utf-8'))

    def _get_body(self):
        content_length = self.headers['Content-Length']
        if content_length is not None:
            return self.rfile.read(int(content_length))
