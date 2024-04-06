package org.hermes.hermesiotapp.feature_sensors.data.repository

import android.util.Log
import org.hermes.hermesiotapp.common.Constants
import org.hermes.hermesiotapp.feature_sensors.data.connection.ISensorApi
import org.hermes.hermesiotapp.feature_sensors.data.dto.SensorDto
import org.hermes.hermesiotapp.feature_sensors.domain.repository.ISensorRepository
import javax.inject.Inject

class SensorRepositoryImp @Inject constructor(
    private val api: ISensorApi
): ISensorRepository {

    override suspend fun getSensors(): List<SensorDto> {
        return try {
            Log.d("SensorRepositoryImp", "Calling: ${Constants.IOT_BASE_URL}api/sensors/")
            api.getSensors()
        } catch (e: Exception) {
            Log.e("SensorRepositoryImp", e.toString())
            emptyList()
        }
    }

    override suspend fun getSensor(id: String): SensorDto = api.getSensor(id)
}