# from parlai.scripts.interactive import Interactive
#
# Interactive.main(
#     model_file='zoo:blender/blender_90M/model'
# )
from parlai.core.agents import create_agent
from parlai.core.loader import register_script
from parlai.core.params import ParlaiParser
from parlai.core.script import ParlaiScript
from parlai.for_test.interactive_app.human_agent import HumanAgent
from parlai.for_test.interactive_app.multi_client_interactive_world import MultiClientInteractiveWorld
from parlai.utils import logging
from parlai.utils.world_logging import WorldLogger


def setup_args(parser=None):
    if parser is None:
        parser = ParlaiParser(
            True, True, 'Interactive chat with a model on the command line'
        )
    parser.add_argument('-d', '--display-examples', type='bool', default=False)
    parser.add_argument(
        '--display-prettify',
        type='bool',
        default=False,
        help='Set to use a prettytable when displaying '
        'examples with text candidates',
    )
    parser.add_argument(
        '--display-ignore-fields',
        type=str,
        default='label_candidates,text_candidates',
        help='Do not display these fields',
    )
    parser.add_argument(
        '-it',
        '--interactive-task',
        type='bool',
        default=True,
        help='Create interactive version of task',
    )
    parser.add_argument(
        '--outfile',
        type=str,
        default='',
        help='Saves a json file containing all of the task examples and '
        'model replies. Set to the empty string to not save at all',
    )
    parser.add_argument(
        '--save-format',
        type=str,
        default='conversations',
        choices=['conversations', 'parlai'],
        help='Format to save logs in. conversations is a jsonl format, parlai is a text format.',
    )
    parser.set_defaults(interactive_mode=True, task='interactive')
    HumanAgent.add_cmdline_args(parser)
    WorldLogger.add_cmdline_args(parser)
    return parser


def create_world(opt):
    if isinstance(opt, ParlaiParser):
        logging.error('interactive should be passed opt not Parser')
        opt = opt.parse_args()

    # Create model and assign it to the specified task
    agent = create_agent(opt, requireModelExists=True)
    agent.opt.log()
    human_agent = HumanAgent(opt)

    # set up world logger
    world_logger = WorldLogger(opt) if opt.get('outfile') else None
    world = MultiClientInteractiveWorld(opt, [human_agent, agent])

    # Show some example dialogs:
    # while not world.epoch_done():
    #     world.parley()
    #     if world.epoch_done() or world.get_total_parleys() <= 0:
    #         # chat was reset with [DONE], [EXIT] or EOF
    #         if world_logger is not None:
    #             world_logger.reset()
    #         continue
    #
    #     if world_logger is not None:
    #         world_logger.log(world)
    #     if opt.get('display_examples'):
    #         print("---")
    #         print(world.display())
    #
    # if world_logger is not None:
    #     # dump world acts to file
    #     world_logger.write(opt['outfile'], world, file_format=opt['save_format'])

    return world


@register_script('interactive', aliases=['i'])
class Interactive(ParlaiScript):
    @classmethod
    def setup_args(cls):
        return setup_args()

    def run(self):
        return create_world(self.opt)
