package com.jeanbernad.classifyvalue.feature.camera.presentation

import android.Manifest
import android.R.attr
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.Surface.ROTATION_270
import android.view.Surface.ROTATION_90
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.UseCaseGroup
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jeanbernad.classifyvalue.core.presentation.button.RoundedButton
import com.jeanbernad.classifyvalue.core.presentation.topbar.TopBar
import com.jeanbernad.classifyvalue.core.stringResources.R
import com.jeanbernad.classifyvalue.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

@Composable
fun CameraDigitScreen(
    viewModel: CameraDigitViewModel,
    modifier: Modifier,
    navController: NavController
) {
    CameraDigitScreenInner(
        viewModel = viewModel,
        modifier = modifier,
        navController = navController
    )
}


@Preview(showBackground = true)
@Composable
fun CameraDigitScreenPreview() {
//    CameraDigitScreenInner(
//        modifier = Modifier,
//        navController = rememberNavController()
//    )
}

@Composable
fun DefaultBottomBarScreen(
    modifier: Modifier = Modifier,
    @StringRes toolbar: Int,
    @StringRes title: Int,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = { TopBar(title = R.string.camera) },
        content = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier.padding(vertical = AppTheme.dimensions().size16()),
                    text = stringResource(id = R.string.shot_a_photo),
                    style = AppTheme.typography().text20Bold(),
                )
                content()
            }
        }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraDigitScreenInner(
    viewModel: CameraDigitViewModel,
    modifier: Modifier,
    navController: NavController
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val value = viewModel.classified().collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val executor = ContextCompat.getMainExecutor(context)

    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder().setCaptureMode(CAPTURE_MODE_MAXIMIZE_QUALITY).build()
        )
    }
    DefaultBottomBarScreen(
        modifier = modifier,
        toolbar = R.string.camera,
        title = R.string.camera
    ) {
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
            AndroidView(
                factory = { _ ->
                    val previewView = PreviewView(context)
                    cameraProviderFuture.addListener({
                        val cameraProvider = cameraProviderFuture.get()
                        val preview = androidx.camera.core.Preview.Builder().build()
                        preview.setSurfaceProvider(previewView.surfaceProvider)

                        val cameraSelector = CameraSelector.Builder()
                            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                            .build()

                        val useCaseGroup = UseCaseGroup.Builder()
                            .addUseCase(preview)
                            .addUseCase(imageCaptureUseCase)
                            .setViewPort(previewView.viewPort!!)
                            .build()

                        cameraProvider.unbindAll()
                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            useCaseGroup
                        )
                    }, executor)
                    previewView
                },
                modifier = modifier.fillMaxSize(),
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
            text = if (cameraPermissionState.status.isGranted) {
                R.string.shot_a_photo
            } else {
                R.string.grant_permission
            }
        ) {
            if (cameraPermissionState.status.isGranted) {
                imageCaptureUseCase.takePicture(
                    executor,
                    object : ImageCapture.OnImageCapturedCallback() {
                        override fun onCaptureSuccess(image: ImageProxy) {
                            super.onCaptureSuccess(image)
                            viewModel.classify(image)
                        }
                    })

            } else {
                cameraPermissionState.launchPermissionRequest()
            }
        }
        if (cameraPermissionState.status.isGranted) {
            CameraDigitScreenPermissionGranted(modifier = modifier, value.value)
        } else {
            CameraDigitScreenPermissionDenied(modifier = modifier)
        }
    }

}

@Composable
fun CameraDigitScreenPermissionDenied(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = AppTheme
                    .dimensions()
                    .size16(),
                horizontal = AppTheme
                    .dimensions()
                    .size16()
            ),
        textAlign = TextAlign.Center,
        text = stringResource(id = R.string.camera_permission_denied)
    )
}

@Composable
fun CameraDigitScreenPermissionGranted(
    modifier: Modifier = Modifier,
    classifiedAndProbability: Pair<String, String>
) {
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
            text = stringResource(id = R.string.classified_as) + ":",
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
            text = stringResource(id = R.string.probability) + ":",
            style = AppTheme.typography().text16Bold(),
        )
        Text(
            modifier = modifier,
            text = classifiedAndProbability.second,
            style = AppTheme.typography().text16Bold(),
        )
    }
}