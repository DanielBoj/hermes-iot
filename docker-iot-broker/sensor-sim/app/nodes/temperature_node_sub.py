from paho.mqtt import client as mqtt

from .client import connect_to_mqtt

TOPIC = "temperature_sensor"

client_id = 'python-mqtt-pond-temp-sub-1'


def subscribe(client: mqtt, topic=TOPIC):
    def on_message(client, userdata, msg):
        print(f"Received message: `{msg.payload.decode('utf-8')}` from topic: `{msg.topic}` on Client: `{client._client_id}`")

    client.subscribe(topic)
    client.on_message = on_message


def run():
    client = connect_to_mqtt(client_id)
    subscribe(client)
    client.loop_forever()


if __name__ == '__main__':
    run()
