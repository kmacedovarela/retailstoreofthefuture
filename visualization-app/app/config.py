import os
import sys

from app import logger


def validate_and_crash(variable, message):
    if not variable:
        logger.error(message)
        sys.exit(message)


logger.info('Reading environment variables...')

CUSTOMERS_LIST_FILE = os.getenv('CUSTOMERS_LIST_FILE', 'app/resources/customers.json')

MQTT_HOST = os.getenv('MQTT_HOST')
MQTT_PORT = int(os.getenv('MQTT_PORT', 1883))
MQTT_NAME = os.getenv('MQTT_NAME', 'demoVisClient')

CUSTOMER_ENTER_TOPIC = os.getenv('ENTER_TOPIC', 'customer/enter')
CUSTOMER_EXIT_TOPIC = os.getenv('EXIT_TOPIC', 'customer/exit')
CUSTOMER_MOVE_TOPIC = os.getenv('MOVE_TOPIC', 'customer/move')

SCENARIO_PLAYER_SCENARIO_ENDPOINT = os.getenv('SCENARIO_PLAYER_SCENARIO_ENDPOINT')

REQUIRED_PARAM_MESSAGE = 'Cannot read {} env variable. Please, make sure it is set before starting the service.'
validate_and_crash(SCENARIO_PLAYER_SCENARIO_ENDPOINT, REQUIRED_PARAM_MESSAGE.format('SCENARIO_PLAYER_SCENARIO_ENDPOINT'))
validate_and_crash(MQTT_HOST, REQUIRED_PARAM_MESSAGE.format('MQTT_HOST'))