package org.hermes.hermesiotapp.feature_sensors.presentation.sensor_detail

import org.hermes.hermesiotapp.feature_sensors.domain.model.Sensor

data class SensorDetailState(
    val sensorRecord: List<Sensor> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)