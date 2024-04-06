package org.hermes.hermesiotapp.feature_sensors.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.hermes.hermesiotapp.R
import org.hermes.hermesiotapp.feature_sensors.presentation.Screen
import org.hermes.hermesiotapp.ui.theme.DeepBlue

@Composable
fun TopBar(
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(DeepBlue)
        .padding(8.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home -> Go back to Home",
                modifier = Modifier
                    .clickable { navController.navigate(Screen.ZoneListScreen.route) }
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back -> Go back to previous screen",
                modifier = Modifier
                    .clickable { navController.popBackStack() }
            )

            Icon(
                painter = painterResource(id = R.drawable.hermes_logo_fordark),
                contentDescription = "Hermes IoT Logo",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(id = R.string.app_title),
            style = MaterialTheme.typography.titleLarge
        )
    }

}