package com.jeanbernad.classifyvalue.feature.paint.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeanbernad.classifyvalue.core.presentation.button.RoundedButton
import com.jeanbernad.classifyvalue.core.presentation.button.RoundedDisButton
import com.jeanbernad.classifyvalue.core.presentation.topbar.TopBar
import com.jeanbernad.classifyvalue.theme.AppTheme
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.rememberDrawController
import com.jeanbernad.classifyvalue.core.stringResources.R as strResR


@Composable
fun PaintDigitScreen(
    viewModel: PaintDigitViewModel,
    modifier: Modifier,
    navController: NavController
) {
    val value = viewModel.classified().collectAsState()
    PaintDigitScreenInner(
        classifiedAndProbability = value.value,
        modifier = modifier,
        navController = navController,
        classifyCallback = { bitmap ->
            viewModel.classify(bitmap!!)
        },
        resetCallback = {
            viewModel.resetClassified()
        })
}


@Preview(showBackground = true)
@Composable
fun PaintDigitScreenPreview() {
    PaintDigitScreenInner(
        classifiedAndProbability = "7" to "0.8",
        modifier = Modifier,
        navController = rememberNavController(),
        classifyCallback = {},
        resetCallback = {}
    )
}

@Composable
fun PaintDigitScreenInner(
    classifiedAndProbability: Pair<String, String>,
    modifier: Modifier,
    navController: NavController,
    classifyCallback: ((bitmap: Bitmap?) -> Unit),
    resetCallback: (() -> Unit)
) {
    val controller = rememberDrawController()
    controller.changeColor(AppTheme.colors().secondMonochrome())
    controller.changeBgColor(AppTheme.colors().firstMonochrome())
    controller.changeStrokeWidth(AppTheme.dimensions().size48().value)

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = { TopBar(title = strResR.string.paint) },
        content = {
            Column(
                modifier = modifier
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier.padding(vertical = AppTheme.dimensions().size16()),
                    text = stringResource(id = strResR.string.draw_a_number),
                    style = AppTheme.typography().text20Bold(),
                )
                Card(
                    modifier = modifier
                        .padding(
                            vertical = AppTheme
                                .dimensions()
                                .size8()
                        )
                        .height(height = LocalConfiguration.current.screenHeightDp.dp / 4)
                        .width(width = LocalConfiguration.current.screenHeightDp.dp / 4),
                    shape = RoundedCornerShape(size = AppTheme.dimensions().size12())
                ) {
                    DrawBox(
                        drawController = controller,
                        bitmapCallback = { bitmap, _ ->
                            classifyCallback(bitmap!!.asAndroidBitmap())
                        },
                        backgroundColor = AppTheme.colors().firstMonochrome(),
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AppTheme
                                .dimensions()
                                .size48(),
                            vertical = AppTheme
                                .dimensions()
                                .size8()
                        )
                ) {
                    Text(
                        modifier = modifier.weight(weight = 1f),
                        text = stringResource(id = strResR.string.classified_as) + ":",
                        style = AppTheme.typography().text16Bold(),
                    )
                    Text(
                        modifier = modifier,
                        text = classifiedAndProbability.first,
                        style = AppTheme.typography().text16Bold(),
                    )
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AppTheme
                                .dimensions()
                                .size48(),
                            vertical = AppTheme
                                .dimensions()
                                .size8()
                        )
                ) {
                    Text(
                        modifier = modifier.weight(weight = 1f),
                        text = stringResource(id = strResR.string.probability) + ":",
                        style = AppTheme.typography().text16Bold(),
                    )
                    Text(
                        modifier = modifier,
                        text = classifiedAndProbability.second,
                        style = AppTheme.typography().text16Bold(),
                    )
                }
                RoundedButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AppTheme
                                .dimensions()
                                .size48(),
                            vertical = AppTheme
                                .dimensions()
                                .size8()
                        ),
                    text = strResR.string.classify
                ) {
                    controller.saveBitmap()
                }
                RoundedDisButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = AppTheme
                                .dimensions()
                                .size48(),
                            vertical = AppTheme
                                .dimensions()
                                .size8()
                        ),
                    text = strResR.string.reset
                ) {
                    controller.reset()
                    resetCallback()
                }
            }
        }
    )
}