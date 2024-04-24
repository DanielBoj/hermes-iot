from paho.mqtt import client as mqtt
import time
import os
import logging

import common.app_constants as app_constants

LOOP_INTERVAL = 2
LOOP_LIMIT = 10

username = os.getenv('MQTT_USERNAME')
password = os.getenv('MQTT_PASSWORD')
broker = os.getenv('MQTT_BROKER')
port = int(os.getenv('MQTT_PORT'))


def connect_to_mqtt(client_id: str) -> mqtt.Client:

    def on_connect(client, userdata, flags, reason_code, properties=None):

        if (reason_code == 0):
            print(f"Connected successfully to the broker. Welcome to the Matrixm Neo! as client: {client._client_id}")
        else:
            print("Failed to connect to the broker. Reason: ", reason_code)

    def on_disconnect(client, userdata, reason_code):

        logging.info(
            "Disconnected from the broker, details with Reason: ", reason_code)

        reconnect_count, reconnect_delay = 0, app_constants.FIRST_RECONNECT_DELAY

        while reconnect_count < app_constants.MAX_RECONNECT_COUNT:
            logging.info("Reconnecting to the broker in ",
                         reconnect_delay, " seconds")
            time.sleep(reconnect_delay)

            try:
                client.reconnect()
                logging.info("Reconnected to the broker successfully")
                return
            except Exception as e:
                logging.error("Failed to reconnect to the broker. Reason: ", e)

            reconnect_count += 1
            reconnect_delay = min(
                reconnect_delay * app_constants.RECONNECT_RATE, app_constants.MAX_RECONNECT_DELAY)

        print("Disconnected from the broker. Reason: ", reason_code)

    client = mqtt.Client(client_id=client_id,
                         callback_api_version=mqtt.CallbackAPIVersion.VERSION2)
    # client.tls_set(ca_certs='./broker.emqx.io-ca.crt')

    #client.username_pw_set(username, password)
    client.on_connect = on_connect
    client.on_disconnect = on_disconnect
    client.connect(broker, port, 60)

    return client
