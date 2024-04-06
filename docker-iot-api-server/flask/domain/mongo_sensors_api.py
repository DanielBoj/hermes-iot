from flask import Blueprint
from .handlers import get_sensors, get_sensor, insert_sensor

api_blueprint = Blueprint('api', __name__, url_prefix='/api')

api_blueprint.route('/sensors', methods=['GET'])(get_sensors)

api_blueprint.route('/sensors/<sensor_id>', methods=['GET'])(get_sensor)

api_blueprint.route('/sensors/add_data', methods=['POST'])(insert_sensor)
