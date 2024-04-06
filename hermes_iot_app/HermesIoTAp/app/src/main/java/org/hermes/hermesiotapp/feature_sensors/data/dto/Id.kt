package org.hermes.hermesiotapp.feature_sensors.data.dto

import com.google.gson.annotations.SerializedName

data class Id(
    @SerializedName("\$oid")
    val id: String
)