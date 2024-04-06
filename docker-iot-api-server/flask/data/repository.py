from .utils import sensors_collection
from domain.result import Result, Data, Error


class Repository:
    def __init__(self):
        self.sensors = sensors_collection

    def get_sensors(self) -> Result:
        try:
            data = self.sensors.find()
            return Data(data=data)
        except Exception as e:
            return Error(error_message=str(e))

    def get_sensor(self, sensor_id: str) -> Result:
        try:
            data = self.sensors.find_one(sensor_id)
            return Data(data=data)
        except Exception as e:
            return Error(error_message=str(e))

    def insert_sensor(self, sensor: dict) -> Result:
        try:
            self.sensors.insert_one(sensor)
            return Data(data="Object inserted")
        except Exception as e:
            return Error(error_message=str(e))
