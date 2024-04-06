package org.hermes.hermesiotapp.feature_sensors.domain.use_case.get_sensor

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.hermes.hermesiotapp.feature_sensors.domain.model.Sensor
import org.hermes.hermesiotapp.feature_sensors.domain.repository.ISensorRepository
import org.hermes.hermesiotapp.feature_sensors.domain.util.Resource
import retrofit2.HttpException
import javax.inject.Inject

class GetSensorUseCase @Inject constructor(
    private val repository: ISensorRepository
) {

    operator fun invoke(
        sensorId: String
    ): Flow<Resource<List<Sensor>>> = flow {

        try {
            emit(Resource.Loading())

            val sensorRecord: List<Sensor> = repository.getSensors()
                .filter { it.sensorId == sensorId }
                .sortedByDescending { it.timestamp }
                .map { it.toSensor() }

            emit(Resource.Success(sensorRecord))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occurred"))
        }
    }
}