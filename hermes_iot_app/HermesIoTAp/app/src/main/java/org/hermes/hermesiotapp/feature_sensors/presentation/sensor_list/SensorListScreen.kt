package org.hermes.hermesiotapp.feature_sensors.presentation.sensor_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.hermes.hermesiotapp.feature_sensors.domain.model.Zone
import org.hermes.hermesiotapp.feature_sensors.presentation.Screen
import org.hermes.hermesiotapp.feature_sensors.presentation.sensor_list.components.SensorListItem
import org.hermes.hermesiotapp.feature_weather.presentation.WeatherScreen

@Composable
fun SensorListScreen(
    navController: NavController,
    viewModel: SensorListViewModel = hiltViewModel(),
    zone: Zone,
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {

        Column( modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = zone.image),
                contentDescription = "The map of the pond.",
                alignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = zone.name,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = zone.description,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
            ) {
                items(state.sensors) { sensor ->
                    SensorListItem(
                        sensor = sensor,
                        onItemClick = {
                            navController.navigate(Screen.SensorDetailScreen.route + "/${sensor.id}")
                        }
                    )
                }
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }

    }
}