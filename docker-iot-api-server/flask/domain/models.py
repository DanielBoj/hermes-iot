from time import time
from enum import Enum


class SensorEntity:

    def __init__(self, sensor_id, sensor_type, sensor_value, timestamp):
        self.sensor_id = sensor_id
        self.sensor_type = sensor_type
        self.sensor_value = sensor_value
        self.timestamp = timestamp


class SensorTypeEntity:

    def __init__(self, sensor_type_id, name, description, unit_of_measurement):
        self.sensor_type_id = sensor_type_id
        self.sensor_type_name = name
        self.sensor_type_description = description
        self.sensor_type_unit_of_measurement = unit_of_measurement


class SensorTypes(Enum):
    TEMPERATURE = 'temperature'
    HUMIDITY = 'humidity'
    PRESSURE = 'pressure'
    VOLUME = 'volume'
    LIGHT = 'light'
    PH = 'ph'


def create_sensor_entity(sensor_id, sensor_type: str, value, timestamp=time()):
    switch = {
        SensorTypes.TEMPERATURE.value: SensorTypeEntity(SensorTypes.TEMPERATURE.value,
                                                        SensorTypes.TEMPERATURE.value,
                                                        f'{SensorTypes.TEMPERATURE.value} sensor',
                                                        'Celsius'),
        SensorTypes.HUMIDITY.value: SensorTypeEntity(SensorTypes.HUMIDITY.value,
                                                     SensorTypes.HUMIDITY.value,
                                                     f'{SensorTypes.HUMIDITY.value} sensor',
                                                     '%'),
        SensorTypes.PRESSURE.value: SensorTypeEntity(SensorTypes.PRESSURE.value,
                                                     SensorTypes.PRESSURE.value,
                                                     f'{SensorTypes.PRESSURE.value} sensor',
                                                     'hPa'),
        SensorTypes.VOLUME.value: SensorTypeEntity(SensorTypes.VOLUME.value,
                                                   SensorTypes.VOLUME.value,
                                                   f'{SensorTypes.VOLUME.value} sensor',
                                                   'm3'),
        SensorTypes.LIGHT.value: SensorTypeEntity(SensorTypes.LIGHT.value,
                                                  SensorTypes.LIGHT.value,
                                                  f'{SensorTypes.LIGHT.value} sensor',
                                                  'lux'),
        SensorTypes.PH.value: SensorTypeEntity(SensorTypes.PH.value,
                                               SensorTypes.PH.value,
                                               f'{SensorTypes.PH.value} sensor',
                                               'pH')
    }

    actual_type = switch[sensor_type]

    return SensorEntity(sensor_id, actual_type, value, timestamp)


if __name__ == '__main__':
    sensor = create_sensor_entity('temp1', 'temperature', 30)
    print(sensor.__dict__)
