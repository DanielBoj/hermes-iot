package org.hermes.hermesiotapp.feature_sensors.data.connection

import org.hermes.hermesiotapp.feature_sensors.data.dto.SensorDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ISensorApi {

    @GET("/api/sensors")
    suspend fun getSensors(): List<SensorDto>

    @GET("/api/sensors/{id}")
    suspend fun getSensor(@Path("id") id: String): SensorDto

}