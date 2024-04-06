package org.hermes.hermesiotapp.feature_weather.data.dto

import com.google.gson.annotations.SerializedName
import org.hermes.hermesiotapp.feature_weather.domain.model.WeatherData
import org.hermes.hermesiotapp.feature_weather.domain.model.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class WeatherDataDto(
    val time: List<String>,
    @SerializedName("temperature_2m")
    val temperatures: List<Double>,
    @SerializedName("weathercode")
    val weatherCodes: List<Int>,
    @SerializedName("pressure_msl")
    val pressures: List<Double>,
    @SerializedName("windspeed_10m")
    val windSpeeds: List<Double>,
    @SerializedName("relativehumidity_2m")
    val humidities: List<Double>
) {

    fun toWeatherDataMap(): Map<Int, List<WeatherData>> {
        return time.mapIndexed { index, time ->
            val temperature = temperatures[index]
            val weatherCode = weatherCodes[index]
            val windSpeed = windSpeeds[index]
            val pressure = pressures[index]
            val humidity = humidities[index]

            IndexedWeatherData(
                index = index,
                data = WeatherData(
                    time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                    temperatureCelsius = temperature,
                    pressureLevel = pressure,
                    windSpeed = windSpeed,
                    humidity = humidity,
                    weatherType = WeatherType.fromWMO(weatherCode)
                )
            )
        }.groupBy {
            it.index / 24
        }.mapValues { entry ->
            entry.value.map { it.data }
        }
    }
}

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)