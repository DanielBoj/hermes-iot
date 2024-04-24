package org.hermes.hermesiotapp.feature_sensors.domain.use_case.get_sensors

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import org.hermes.hermesiotapp.feature_sensors.domain.model.Sensor
import org.hermes.hermesiotapp.feature_sensors.domain.repository.ISensorRepository
import org.hermes.hermesiotapp.feature_sensors.domain.util.Resource
import retrofit2.HttpException
import javax.inject.Inject

class GetSensorsUseCase @Inject constructor(
    private val repository: ISensorRepository
) {

    operator fun invoke(): Flow<Resource<List<Sensor>>> = flow {

        try {
            emit(Resource.Loading())

            val sensors = repository.getSensors()
                .distinctBy { it.sensorId }
                .sortedBy { it.sensorId }
                .map {sensorDto -> sensorDto.toSensor() }

            emit(Resource.Success(sensors))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage?: "An unexpected error occurred"))
        }
    }
}