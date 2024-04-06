package org.hermes.hermesiotapp.feature_weather.data.dto

import com.google.gson.annotations.SerializedName
import org.hermes.hermesiotapp.feature_weather.domain.model.WeatherInfo
import java.time.LocalDateTime

data class WeatherDto(

    @SerializedName("hourly")
    val weatherData: WeatherDataDto
) {

    fun toWeatherInfo(): WeatherInfo {
        val weatherDataMap = weatherData.toWeatherDataMap()
        val now = LocalDateTime.now()
        val currentWeatherData = weatherDataMap[0]?.find {data ->
            val hour = if (now.minute < 30) now.hour else now.hour + 1
            data.time.hour == hour
        }

        return WeatherInfo(
            weatherDataPerDay = weatherDataMap,
            currentWeatherData = currentWeatherData
        )
    }
}