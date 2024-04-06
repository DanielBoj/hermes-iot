package org.hermes.hermesiotapp.feature_weather.data.repository

import android.util.Log
import org.hermes.hermesiotapp.feature_sensors.domain.util.Resource
import org.hermes.hermesiotapp.feature_weather.data.connection.IWeatherApi
import org.hermes.hermesiotapp.feature_weather.domain.model.WeatherInfo
import org.hermes.hermesiotapp.feature_weather.domain.repository.IWeatherRepository
import javax.inject.Inject

class WeatherRepositoryImp @Inject constructor(
    private val api: IWeatherApi
): IWeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            Log.e("WeatherRepositoryImp", "getWeatherData: ${e.message}", e)
            Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
        }
    }
}