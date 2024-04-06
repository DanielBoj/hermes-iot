package org.hermes.hermesiotapp.feature_sensors.presentation.sensor_list

import org.hermes.hermesiotapp.feature_sensors.domain.model.Sensor

data class SensorListState(
    val sensors: List<Sensor> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)