package org.hermes.hermesiotapp.feature_sensors.domain.repository

import org.hermes.hermesiotapp.feature_sensors.data.dto.SensorDto

interface ISensorRepository {

    suspend fun getSensors(): List<SensorDto>

    suspend fun getSensor(id: String): SensorDto
}