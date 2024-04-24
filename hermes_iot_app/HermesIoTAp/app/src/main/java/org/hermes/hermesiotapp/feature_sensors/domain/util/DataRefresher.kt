package org.hermes.hermesiotapp.feature_sensors.domain.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration

class DataRefresher(
    private val refreshInterval: Duration,
    private val scope:CoroutineScope,
    private val onRefresh: () -> Unit
) {

    operator fun invoke(): Job {

        return scope.launch {
            while (true) {
                delay(refreshInterval)
                (refreshInterval)
                onRefresh()
            }
        }
    }
}