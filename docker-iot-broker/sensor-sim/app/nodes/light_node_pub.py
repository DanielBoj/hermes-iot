from paho.mqtt import client as mqtt
import time
import random
import os
import logging

from .client import connect_to_mqtt
from common import app_constants


LOOP_INTERVAL = 2
LOOP_LIMIT = 10

client_id = 'python-mqtt-pond-light-pub-1'


def publish_message(client, topic=app_constants.LIGHT_TOPIC):

    time.sleep(1)
    message_count = 0

    while True:

        message = round(random.uniform(0.01, 100000.00), 2)
        result = client.publish(topic, str(message), qos=1)

        status = result[0]
        if status == 0:
            print(f"Message `{message}` published successfully to topic: `{topic}` ")
            logging.info("Message published successfully to topic: ", topic)
        else:
            print("Failed to publish message to topic: ", topic)
            logging.error("Failed to publish message to topic: ", topic)

        if message_count > LOOP_LIMIT:
            break

        message_count += 1
        time.sleep(LOOP_INTERVAL)


def run():
    client = connect_to_mqtt(client_id)
    client.loop_start()
    publish_message(client)
    client.loop_stop()


if __name__ == '__main__':
    run()
