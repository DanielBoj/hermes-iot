package org.hermes.hermesiotapp.feature_sensors.data.dto

import com.google.gson.annotations.SerializedName
import org.hermes.hermesiotapp.feature_sensors.domain.model.SensorType

data class SensorTypeDto(
    @SerializedName("sensor_type_id")
    val id: String,
    @SerializedName("sensor_type_description")
    val description: String,
    @SerializedName("sensor_type_name")
    val name: String,
    @SerializedName("sensor_type_unity_of_measurement")
    val unityOfMeasurement: String
) {
    fun toSensorType() = SensorType(
        id = id,
        description = description,
        name = name,
        unityOfMeasurement = unityOfMeasurement
    )
}