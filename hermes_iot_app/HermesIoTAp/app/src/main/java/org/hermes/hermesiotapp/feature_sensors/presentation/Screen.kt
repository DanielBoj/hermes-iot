package org.hermes.hermesiotapp.feature_sensors.presentation

sealed class Screen(
    val route: String
) {
    data object ZoneListScreen: Screen("zone_list_screen")
    data object SensorListScreen: Screen("sensor_list_screen")
    data object SensorDetailScreen: Screen("sensor_detail_screen")
}