package org.hermes.hermesiotapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.hermes.hermesiotapp.feature_sensors.domain.model.Zone
import org.hermes.hermesiotapp.feature_sensors.presentation.Screen
import org.hermes.hermesiotapp.feature_sensors.presentation.components.TopBar
import org.hermes.hermesiotapp.feature_sensors.presentation.sensor_detail.SensorDetailScreen
import org.hermes.hermesiotapp.feature_sensors.presentation.sensor_list.SensorListScreen
import org.hermes.hermesiotapp.feature_sensors.presentation.zone_list.ZoneListScreen
import org.hermes.hermesiotapp.ui.theme.DarkBlue
import org.hermes.hermesiotapp.ui.theme.HermesIoTApTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            HermesIoTApTheme {

                val navController = rememberNavController()

                Scaffold(
                    Modifier.fillMaxSize(),
                    topBar = { TopBar(navController = navController) },
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        color = DarkBlue
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = Screen.ZoneListScreen.route
                        ) {
                            composable(
                                route = Screen.ZoneListScreen.route
                            ) {
                                ZoneListScreen(navController = navController)
                            }

                            composable(
                                route = Screen.SensorListScreen.route
                            ) {
                                SensorListScreen(navController = navController, zone = Zone.Pond)
                            }

                            composable (
                                route = Screen.SensorDetailScreen.route + "/{sensorId}"
                            ) {
                                SensorDetailScreen(navController = navController)
                            }
                        }
                    }
                }

            }
        }
    }
}