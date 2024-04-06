package org.hermes.hermesiotapp.feature_sensors.domain.model

import org.hermes.hermesiotapp.R

sealed class Zone(
    val name: String,
    val description: String,
    val image: Int
) {
    data object Pond: Zone(
        name = "Pond",
        description = "The pond is a body of water that is surrounded by land.",
        image = R.drawable.pond_map
    )

    data object Australian: Zone(
        name = "Australian",
        description = "The Australian zone is a place where you can find plants native to Australia.",
        image = R.drawable.australian_map
    )

    data object SouthAfrican: Zone(
        name = "African",
        description = "The African zone is a place where you can find plants native to Africa.",
        image = R.drawable.south_african_map
    )

    data object Chilean: Zone(
        name = "Chilean",
        description = "The Chilean zone is a place where you can find plants native to Chile.",
        image = R.drawable.chilean_map
    )

    data object OrientalMediterranean: Zone(
        name = "Mediterranean",
        description = "The Mediterranean zone is a place where you can find plants native to the Mediterranean.",
        image = R.drawable.oriental_mediterranean_map
    )

    data object OccidentalMediterranean: Zone(
        name = "Occidental Mediterranean",
        description = "The Occidental Mediterranean zone is a place where you can find plants native to the Occidental Mediterranean.",
        image = R.drawable.occidental_mediterranean_map
    )

    data object Californian: Zone(
        name = "Californian",
        description = "The Californian zone is a place where you can find plants native to California.",
        image = R.drawable.californian_map
    )

    data object NorthAfrican: Zone(
        name = "North Africa",
        description = "The North Africa zone is a place where you can find plants native to North Africa.",
        image = R.drawable.north_african_map
    )

    data object CanaryIslands: Zone(
        name = "Canary Islands",
        description = "The Canary Islands zone is a place where you can find plants native to the Canary Islands.",
        image = R.drawable.canary_islands_map
    )
}