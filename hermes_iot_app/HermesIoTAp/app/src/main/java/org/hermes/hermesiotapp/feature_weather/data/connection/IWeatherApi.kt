package org.hermes.hermesiotapp.feature_weather.data.connection

import org.hermes.hermesiotapp.feature_weather.data.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {

    @GET("v1/forecast?hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") long: Double
    ): WeatherDto
}