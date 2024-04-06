package org.hermes.hermesiotapp.feature_weather.domain.repository

import org.hermes.hermesiotapp.feature_sensors.domain.util.Resource
import org.hermes.hermesiotapp.feature_weather.domain.model.WeatherInfo

interface IWeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}