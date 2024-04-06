package org.hermes.hermesiotapp.feature_weather.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.hermes.hermesiotapp.common.Constants
import org.hermes.hermesiotapp.feature_sensors.domain.util.Resource
import org.hermes.hermesiotapp.feature_weather.domain.repository.IWeatherRepository
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: IWeatherRepository
): ViewModel(
) {

    private val _state = mutableStateOf<WeatherState>(WeatherState())
    val state: State<WeatherState> = _state

    init {
        getWeatherInfo()
        Log.d("WeatherViewModel", "init: Data fetched")
    }

    fun getWeatherInfo(
        lat: Double = Constants.BOTANICAL_GARDEN_LAT,
        long: Double = Constants.BOTANICAL_GARDEN_LONG
    ) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                error = ""
            )

            when (val result = repository.getWeatherData(lat, long)) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        weatherInfo = result.data,
                        error = ""
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        weatherInfo = null,
                        isLoading = false,
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(
                        isLoading = true
                    )
                }
            }


        }
    }
}