package org.hermes.hermesiotapp.feature_sensors.presentation.zone_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.hermes.hermesiotapp.feature_sensors.domain.model.Zone

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ZoneListItem(
    zone: Zone,
    onItemClick: (Zone) -> Unit,
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onItemClick(zone) }
    ) {
        Text(
            text = zone.name,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Start
        )

        Text(
            text = zone.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start
        )

        Divider()

        Image(
            painter = painterResource(id = zone.image),
            contentDescription = "Map of ${zone.name}",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
    }

}