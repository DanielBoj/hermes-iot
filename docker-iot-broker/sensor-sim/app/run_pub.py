import threading

from nodes.temperature_node_pub import run as run_temperature_node
from nodes.humidity_node_pub import run as run_humidity_node
from nodes.pressure_node_pub import run as run_pressure_node
from nodes.light_node_pub import run as run_light_node
from nodes.water_level_node_pub import run as run_water_level_node
from nodes.soil_ph_node_pub import run as run_soil_ph_node


def temperature_worker():
    run_temperature_node()


def humidity_worker():
    run_humidity_node()


def pressure_worker():
    run_pressure_node()


def light_worker():
    run_light_node()


def water_level_worker():
    run_water_level_node()


def soil_ph_worker():
    run_soil_ph_node()


def run():

    threads = []
    threads.append(threading.Thread(target=temperature_worker))
    threads.append(threading.Thread(target=humidity_worker))
    threads.append(threading.Thread(target=pressure_worker))
    threads.append(threading.Thread(target=light_worker))
    threads.append(threading.Thread(target=water_level_worker))
    threads.append(threading.Thread(target=soil_ph_worker))

    for thread in threads:
        thread.start()

    for thread in threads:
        thread.join()



if __name__ == '__main__':
    run()
