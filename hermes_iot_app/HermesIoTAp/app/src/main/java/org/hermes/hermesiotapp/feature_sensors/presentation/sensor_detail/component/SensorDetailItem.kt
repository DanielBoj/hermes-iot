package org.hermes.hermesiotapp.feature_sensors.presentation.sensor_detail.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.hermes.hermesiotapp.feature_sensors.domain.model.Sensor
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Composable
fun SensorDetailItem(
    sensor: Sensor
) {
    val time = LocalDateTime.ofEpochSecond(sensor.timestamp, 0, ZoneOffset.UTC)

    Box (modifier = Modifier
        .fillMaxWidth()
    ) {
        Row( modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${sensor.value} ${sensor.unit}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = time.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        HorizontalDivider()
    }
}