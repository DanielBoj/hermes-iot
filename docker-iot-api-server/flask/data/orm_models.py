import json
from domain.models import SensorEntity, SensorTypeEntity, create_sensor_entity, SensorTypes


def map_sensor_entity_to_dict(sensor_entity: SensorEntity) -> dict:
    sensor_dict = {
        'sensor_id': sensor_entity.sensor_id,
        'sensor_type': map_sensor_type_to_dict(sensor_entity.sensor_type),
        'sensor_value': sensor_entity.sensor_value,
        'timestamp': sensor_entity.timestamp
    }

    return sensor_dict


def map_sensor_type_to_dict(sensor_type: SensorTypeEntity) -> dict:
    sensor_type_dict = {
        'sensor_type_id': sensor_type.sensor_type_id,
        'sensor_type_name': sensor_type.sensor_type_name,
        'sensor_type_description': sensor_type.sensor_type_description,
        'sensor_type_unity_of_measurement': sensor_type.sensor_type_unit_of_measurement
    }

    return sensor_type_dict


def map_sensor_to_json(sensor: SensorEntity):
    sensor_dict = map_sensor_entity_to_dict(sensor)

    return json.dumps(sensor_dict)


if __name__ == '__main__':
    act_sensor = create_sensor_entity('temp1', SensorTypes.TEMPERATURE.value, 30)
    act_sensor_dict = map_sensor_entity_to_dict(act_sensor)
    sensor_json = map_sensor_to_json(act_sensor)
    print(sensor_json)
