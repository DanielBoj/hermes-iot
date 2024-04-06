package org.hermes.hermesiotapp.feature_sensors.domain.model

data class SensorType(
    val id: String,
    val description: String,
    val name: String,
    val unityOfMeasurement: String
)
