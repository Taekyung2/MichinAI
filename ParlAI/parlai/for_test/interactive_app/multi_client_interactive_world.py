from copy import deepcopy
from parlai.core.worlds import DialogPartnerWorld, validate
from time import time


class MultiClientInteractiveWorld(DialogPartnerWorld):
    # agents = [base_human_agent, base_agent]
    def __init__(self, opt, agents, shared=None):
        super().__init__(opt, agents, shared)

        self.users = {
            'base': agents
        }
        # self.agents = agents

        self.init_contexts(shared=shared)

    def init_contexts(self, shared=None):
        """
        Override to load or instantiate contexts to be used to seed the chat.
        """
        pass

    def get_contexts(self):
        """
        Override to return a pair of contexts with which to seed the episode.

        This function will be called before the first turn of every episode.
        """
        return ['', '']

    def finalize_episode(self, u_id):
        print('{}: CHAT DONE'.format(u_id))
        if not self.epoch_done():
            print("\n... preparing new chat ...\n")

    def new_user(self, u_id):
        print('new user ! : {}'.format(u_id))
        base_agents = self.users['base']
        self.users[u_id] = [base_agents[0].clone(), base_agents[1].clone()]
        return self.users[u_id]

    # def set_agents(self, agents):
    #     self.agents = agents
    #
    # def get_agents(self):
    #     return self.agents

    def parley(self, u_id, msg):
        print('답변 생성 시작 !')

        start = time()
        acts = self.acts
        agents = self.users[u_id] if u_id in self.users.keys() else self.new_user(u_id)
        # agents = self.agents
        
        act = deepcopy(agents[0].act(msg))
        acts[0] = act

        agents[1].observe(validate(act))
        # 여기가 문제야
        # 다른 요청들 다 만들 때 까지 안넘어가
        acts[1] = agents[1].act()
        agents[0].observe(validate(acts[1]))
        self.update_counters()

        print('응답시간 : {}'.format(time() - start))
        return acts[1]
