package org.hermes.hermesiotapp.feature_sensors.presentation.sensor_detail.component

import android.graphics.PorterDuff
import android.util.Log
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberAxisLabelComponent
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.fullWidth
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoScrollState
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.compose.common.shader.color
import com.patrykandpatrick.vico.compose.common.shader.component
import com.patrykandpatrick.vico.compose.common.shader.verticalGradient
import com.patrykandpatrick.vico.compose.common.shape.dashed
import com.patrykandpatrick.vico.core.cartesian.HorizontalLayout
import com.patrykandpatrick.vico.core.cartesian.Scroll
import com.patrykandpatrick.vico.core.cartesian.axis.AxisItemPlacer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.shader.DynamicShader
import com.patrykandpatrick.vico.core.common.shader.TopBottomShader
import com.patrykandpatrick.vico.core.common.shape.Shape
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

@Composable
fun GraphicItem(
    x: List<Long>,
    y: List<Float>
) {

    val modelProducer = remember { CartesianChartModelProducer.build() }
    LaunchedEffect(key1 = Unit) {
        withContext(Dispatchers.Default) {
            modelProducer.tryRunTransaction { lineSeries { series(x = x, y = y) } }
        }
    }

    ViewChart(modelProducer)

}

@Composable
fun ViewChart(
    modelProducer: CartesianChartModelProducer,
){

//    val bottomAxisFormatter = CartesianValueFormatter { x, _, _ -> getTimeFromTimestamp(x.toLong()) }

    val colors = chartColors
    val scrollState = rememberVicoScrollState(initialScroll = Scroll.Absolute.Start)
    val marker = rememberMarker()
    CartesianChartHost(
        chart = rememberCartesianChart(
            rememberLineCartesianLayer(
                lines = listOf(
                    rememberLineSpec(
                        shader = TopBottomShader(
                            DynamicShader.color(colors[0]),
                            DynamicShader.color(colors[1]),
                        ),
                        backgroundShader = TopBottomShader(
                            DynamicShader.compose(
                                DynamicShader.component(
                                    componentSize = 6.dp,
                                    component =
                                    rememberShapeComponent(
                                        shape = Shape.Pill,
                                        color = colors[0],
                                        margins = Dimensions.of(1.dp),
                                    ),
                                ),
                                DynamicShader.verticalGradient(
                                    arrayOf(Color.Black, Color.Transparent),
                                ),
                                PorterDuff.Mode.DST_IN,
                            ),
                            DynamicShader.compose(
                                DynamicShader.component(
                                    componentSize = 5.dp,
                                    component =
                                    rememberShapeComponent(
                                        shape = Shape.Rectangle,
                                        color = colors[1],
                                        margins = Dimensions.of(horizontal = 2.dp),
                                    ),
                                    checkeredArrangement = false,
                                ),
                                DynamicShader.verticalGradient(
                                    arrayOf(Color.Transparent, Color.Black),
                                ),
                                PorterDuff.Mode.DST_IN,
                            ),
                        ),
                    ),
                ),
            ),
            startAxis = rememberStartAxis(
                label = rememberAxisLabelComponent(
                    color = MaterialTheme.colorScheme.onBackground,
                    background =
                    rememberShapeComponent(
                        shape = Shape.Pill,
                        color = Color.Transparent,
                        strokeColor = MaterialTheme.colorScheme.outlineVariant,
                        strokeWidth = 1.dp,
                    ),
                    padding = Dimensions.of(horizontal = 6.dp, vertical = 2.dp),
                    margins = Dimensions.of(end = 8.dp),
                ),
                axis = null,
                tick = null,
                guideline = rememberLineComponent(
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape =
                    remember {
                        Shape.dashed(
                            shape = Shape.Pill,
                            dashLength = 4.dp,
                            gapLength = 8.dp,
                        )
                    },
                ),
                itemPlacer = remember { AxisItemPlacer.Vertical.count(count = { 4 }) },
            ),
            bottomAxis = rememberBottomAxis(
                valueFormatter = CartesianValueFormatter { x, _, _ ->
                    secondsToTime(x.toLong())
                },
                guideline = null,
                itemPlacer =
                remember {
                    AxisItemPlacer.Horizontal.default(
                        spacing = 3,
                        addExtremeLabelPadding = true,
                    )
                },
            ),
        ),
        modelProducer = modelProducer,
        modifier = Modifier,
        marker = marker,
        runInitialAnimation = false,
        horizontalLayout = HorizontalLayout.fullWidth(),
        scrollState = scrollState,
    )
}

private val chartColors
    @ReadOnlyComposable
    @Composable
    get() =
        listOf(
            Color.Blue,
            Color.Red
        )

fun getTimeFromTimestamp(timestamp: Long): String {
    val instant = Instant.ofEpochMilli(timestamp)
    val zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC)
    val localTime = zonedDateTime.toLocalTime()

    return "${localTime.hour}:${localTime.minute}"
}

fun secondsToTime(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val seconds = seconds % 60

    return "$hours:$minutes:$seconds"
}