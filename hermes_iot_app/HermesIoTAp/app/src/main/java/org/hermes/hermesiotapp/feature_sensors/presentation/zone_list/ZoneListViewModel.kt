package org.hermes.hermesiotapp.feature_sensors.presentation.zone_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZoneListViewModel @Inject constructor(

): ViewModel() {

    private val _state = mutableStateOf<ZoneListState>(ZoneListState())
    val state: State<ZoneListState> get() = _state


}