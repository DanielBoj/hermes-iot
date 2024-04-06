from pymongo import MongoClient
import os
from dotenv import load_dotenv

load_dotenv()

uri = os.getenv('MONGO_STRING')
client = MongoClient(uri)
sensors_db = client['iot_sensors_db']
sensors_collection = sensors_db['sensors']


def ping_database_test():
    try:
        client.list_databases()
        return True
    except Exception as e:
        print(e)
        return False


if __name__ == '__main__':
    print(ping_database_test())
