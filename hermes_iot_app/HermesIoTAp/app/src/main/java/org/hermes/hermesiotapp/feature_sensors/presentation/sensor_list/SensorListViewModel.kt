package org.hermes.hermesiotapp.feature_sensors.presentation.sensor_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.hermes.hermesiotapp.feature_sensors.domain.use_case.get_sensors.GetSensorsUseCase
import org.hermes.hermesiotapp.feature_sensors.domain.util.Resource
import javax.inject.Inject

@HiltViewModel
class SensorListViewModel @Inject constructor(
    private val getSensorsUseCase: GetSensorsUseCase
): ViewModel() {

    private val _state = mutableStateOf<SensorListState>(SensorListState())
    val state: State<SensorListState> get() = _state

    init {
        getSensors()
        Log.d("SensorListViewModel", "init called: $this")
    }

    private fun getSensors() {
        getSensorsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = SensorListState(isLoading = true)

                is Resource.Success -> _state.value = SensorListState(sensors = result.data ?: emptyList())

                is Resource.Error -> _state.value = SensorListState(error = result.message ?: "An unexpected error occurred")
            }
        }.launchIn(viewModelScope)
    }
}