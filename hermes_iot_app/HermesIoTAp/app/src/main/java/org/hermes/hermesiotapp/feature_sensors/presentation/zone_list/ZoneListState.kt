package org.hermes.hermesiotapp.feature_sensors.presentation.zone_list

import org.hermes.hermesiotapp.feature_sensors.domain.model.Zone

data class ZoneListState(
    val zones: List<Zone> = ZonesList.zonesList,
    val isLoading: Boolean = false,
    val error: String = ""
)

object ZonesList {
    val zonesList = listOf(
        Zone.Pond,
        Zone.Chilean,
        Zone.Australian,
        Zone.Californian,
        Zone.OrientalMediterranean,
        Zone.OccidentalMediterranean,
        Zone.NorthAfrican,
        Zone.SouthAfrican,
        Zone.CanaryIslands
    )
}
