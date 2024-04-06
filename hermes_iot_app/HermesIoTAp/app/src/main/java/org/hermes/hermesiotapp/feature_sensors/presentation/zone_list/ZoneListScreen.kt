package org.hermes.hermesiotapp.feature_sensors.presentation.zone_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.hermes.hermesiotapp.R
import org.hermes.hermesiotapp.feature_sensors.presentation.Screen
import org.hermes.hermesiotapp.feature_sensors.presentation.zone_list.components.ZoneListItem
import org.hermes.hermesiotapp.feature_weather.presentation.WeatherScreen

@Composable
fun ZoneListScreen (
    navController: NavController,
    viewModel: ZoneListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val lazyListState = rememberLazyListState()
    val scrollState = rememberScrollState()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(id = R.string.zone_list_title),
                style = MaterialTheme.typography.headlineLarge
            )

            HorizontalDivider()

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow(modifier= Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                state = lazyListState
            ) {
                items(state.zones) { zone ->
                    ZoneListItem(
                        zone = zone,
                        onItemClick = {
                            navController.navigate(Screen.SensorListScreen.route /*+ "/${zone}"*/)
                        }
                    )
                }
            }

            HorizontalDivider()

            WeatherScreen()
        }

    }
}