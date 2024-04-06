import time
from flask import Response, request
from bson.json_util import dumps
from data.repository import Repository
from domain.models import create_sensor_entity
from domain.result import Result, Data, Error
from data.orm_models import map_sensor_entity_to_dict

repository = Repository()


def get_sensors():
    try:
        result = repository.get_sensors()
        data = check_result(result)
        return Response(response=data, status=200, mimetype='application/json')
    except Exception as e:
        print(e)
        return Response(response=str(e), status=500, mimetype='application/json')


def get_sensor(sensor_id):
    try:
        result = repository.get_sensor(sensor_id)
        data = check_result(result)
        return Response(response=data, status=200, mimetype='application/json')
    except Exception as e:
        print(e)
        return Response(response=str(e), status=500, mimetype="application/json")


def insert_sensor():
    try:
        payload = request.get_json()

        if not all(key in payload for key in ('sensor_id', 'sensor_type', 'sensor_value')):
            return Response(response='Missing required data', status=400, mimetype='application/json')

        if payload['timestamp']:
            try:
                timestamp = float(payload['timestamp'])
            except ValueError as e:
                print(e)
                return Response(response='Invalid timestamp', status=400, mimetype='application/json')
        else:
            timestamp = time.time()

        try:
            sensor = create_sensor_entity(payload['sensor_id'],
                                          payload['sensor_type'],
                                          payload['sensor_value'],
                                          timestamp)
            sensor_dict = map_sensor_entity_to_dict(sensor)

        except KeyError as e:
            print(e)
            return Response(response='Invalid sensor type', status=400, mimetype='application/json')

        result = repository.insert_sensor(sensor_dict)
        data = check_result(result)
        return Response(response=data, status=200, mimetype='application/json')
    except Exception as e:
        print(e)
        return Response(response=str(e), status=500, mimetype='application/json')


def check_result(result: Result) -> str:
    if result.is_success() and isinstance(result, Data):
        sensor_data = result.data
        return dumps(sensor_data)
    elif result.is_error() and isinstance(result, Error):
        raise Exception(result.error_message)
