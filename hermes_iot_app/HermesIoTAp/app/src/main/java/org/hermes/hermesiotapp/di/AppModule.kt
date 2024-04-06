package org.hermes.hermesiotapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.hermes.hermesiotapp.common.Constants
import org.hermes.hermesiotapp.feature_sensors.data.connection.ISensorApi
import org.hermes.hermesiotapp.feature_sensors.data.repository.SensorRepositoryImp
import org.hermes.hermesiotapp.feature_sensors.domain.repository.ISensorRepository
import org.hermes.hermesiotapp.feature_weather.data.connection.IWeatherApi
import org.hermes.hermesiotapp.feature_weather.data.repository.WeatherRepositoryImp
import org.hermes.hermesiotapp.feature_weather.domain.repository.IWeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSensorApi(): ISensorApi {
        return Retrofit.Builder()
            .baseUrl(Constants.IOT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ISensorApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSensorRepository(api: ISensorApi): ISensorRepository = SensorRepositoryImp(api)

    @Provides
    @Singleton
    fun provideWeatherApi(): IWeatherApi {
        return Retrofit.Builder()
            .baseUrl(Constants.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IWeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(api: IWeatherApi): IWeatherRepository = WeatherRepositoryImp(api)
}