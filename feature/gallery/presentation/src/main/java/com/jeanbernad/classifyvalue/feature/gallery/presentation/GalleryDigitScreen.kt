package com.jeanbernad.classifyvalue.feature.gallery.presentation

import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jeanbernad.classifyvalue.core.presentation.button.RoundedButton
import com.jeanbernad.classifyvalue.core.presentation.topbar.TopBar
import com.jeanbernad.classifyvalue.core.stringResources.R
import com.jeanbernad.classifyvalue.theme.AppTheme
import com.mr0xf00.easycrop.CropResult
import com.mr0xf00.easycrop.CropState
import com.mr0xf00.easycrop.CropperLoading
import com.mr0xf00.easycrop.ImagePicker
import com.mr0xf00.easycrop.RectCropShape
import com.mr0xf00.easycrop.crop
import com.mr0xf00.easycrop.rememberImageCropper
import com.mr0xf00.easycrop.rememberImagePicker
import com.mr0xf00.easycrop.ui.ImageCropperDialog
import io.ak1.drawbox.rememberDrawController
import kotlinx.coroutines.launch

@Composable
fun GalleryDigitScreen(
    viewModel: GalleryDigitViewModel,
    modifier: Modifier,
    navController: NavController
) {
    GalleryDigitScreenInner(
        viewModel = viewModel,
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
fun GalleryDigitScreenPreview() {
//    GalleryDigitScreenInner(
//        classifiedAndProbability = "7" to "0.8",
//        modifier = Modifier,
//        navController = rememberNavController(),
//        classifyCallback = {},
//        resetCallback = {}
//    )
}

//abstract class ClassifyBitmap {
//
//    @Composable
//    abstract fun Bind()
//
//    object Init: ClassifyBitmap() {
//
//        @Composable
//        override fun Bind() = Unit
//    }
//
//    class Image(private val bitmap: Bitmap): ClassifyBitmap {
//
//        @Composable
//        override fun Bind() {
//
//        }
//    }
//}

@Composable
fun GalleryDigitScreenInner(
    viewModel: GalleryDigitViewModel,
    modifier: Modifier,
    navController: NavController,
    classifyCallback: ((bitmap: Bitmap?) -> Unit),
    resetCallback: (() -> Unit)
) {
    val imageCropper = rememberImageCropper()

    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var photo = remember<ImageBitmap?> { null }

    val value = viewModel.classified().collectAsState()

    val imagePicker = rememberImagePicker(onImage = { uri ->
        scope.launch {
            val result = imageCropper.crop(uri, context)
            if (result is CropResult.Success) {
                photo = result.bitmap
                viewModel.classify(result.bitmap.asAndroidBitmap())
            }
        }
    })

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = { TopBar(title = R.string.gallery) },
        content = {
            Column(
                modifier = modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (imageCropper.cropState != null) {
                    ImageCropperDialog(state = imageCropper.cropState!!)
                }
                Text(
                    modifier = modifier.padding(vertical = AppTheme.dimensions().size16()),
                    text = stringResource(id = R.string.choose_from_gallery),
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
                    photo?.let {
                        Image(
                            bitmap = it,
                            contentDescription = null,
                        )
                    }
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
                    text = R.string.choose
                ) {
                    imagePicker.pick()
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
                        text = stringResource(id = R.string.classified_as) + ":",
                        style = AppTheme.typography().text16Bold(),
                    )
                    Text(
                        modifier = modifier,
                        text = value.value.first,
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
                        text = value.value.second,
                        style = AppTheme.typography().text16Bold(),
                    )
                }
            }
        })
}
