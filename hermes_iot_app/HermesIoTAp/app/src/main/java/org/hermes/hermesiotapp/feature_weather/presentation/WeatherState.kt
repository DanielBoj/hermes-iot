package org.hermes.hermesiotapp.feature_weather.presentation

import org.hermes.hermesiotapp.feature_weather.domain.model.WeatherInfo

data class WeatherState(
    val isLoading: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val error: String = ""
)
