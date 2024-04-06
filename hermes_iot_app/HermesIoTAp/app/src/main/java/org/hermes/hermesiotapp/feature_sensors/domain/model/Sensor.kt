package org.hermes.hermesiotapp.feature_sensors.domain.model

data class Sensor(
    val id: String,
    val name: String,
    val loc: String,
    val type: String,
    val unit: String,
    val value: Float,
    val timestamp: Long
) {

    companion object {
        fun create(
            id: String,
            name: String,
            loc: String = "pond",
            type: String,
            unit: String,
            value: Float,
            timestamp: Long
        ): Sensor {
            return Sensor(
                id = id,
                name = name,
                loc = loc,
                type = type,
                unit = unit,
                value = value,
                timestamp = timestamp
            )
        }
    }
}
