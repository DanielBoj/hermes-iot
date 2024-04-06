package org.hermes.hermesiotapp.feature_sensors.data.dto

import com.google.gson.annotations.SerializedName
import org.hermes.hermesiotapp.feature_sensors.domain.model.Sensor

data class SensorDto(
    @SerializedName("_id")
    val id: Id,
    @SerializedName("sensor_id")
    val sensorId: String,
    @SerializedName("sensor_type")
    val sensorTypeDto: SensorTypeDto,
    @SerializedName("sensor_value")
    val sensorValue: Double,
    val timestamp: Double
) {
    fun toSensor(): Sensor = Sensor.create (
        id = this.sensorId,
        name = "${this.sensorTypeDto.name} ${this.sensorId}",
        type = this.sensorTypeDto.name,
        unit = this.sensorTypeDto.unityOfMeasurement,
        value = this.sensorValue.toFloat(),
        timestamp = this.timestamp.toLong()
    )
}