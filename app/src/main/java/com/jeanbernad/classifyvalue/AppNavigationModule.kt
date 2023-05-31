package com.jeanbernad.classifyvalue

import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTab
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTabs
import com.jeanbernad.classifyvalue.feature.camera.CameraQualifier
import com.jeanbernad.classifyvalue.feature.paint.PaintQualifier
import com.jeanbernad.classifyvalue.core.presentation.navigation.NavigationFeatures
import com.jeanbernad.classifyvalue.feature.gallery.GalleryBottomTab
import com.jeanbernad.classifyvalue.feature.gallery.GalleryQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class AppNavigationModule {

    @Provides
    fun provideAppNavigationHost(
        @PaintQualifier
        paintFeatureApi: FeatureApi,
        @CameraQualifier
        cameraFeatureApi: FeatureApi,
        @GalleryQualifier
        galleryFeatureApi: FeatureApi
    ): NavigationFeatures =
        NavigationFeatures.Items(features = listOf(paintFeatureApi, cameraFeatureApi, galleryFeatureApi))

    @Provides
    fun provideAppBottomTabs(
        @PaintQualifier
        paintBottomTab: BottomTab,
        @CameraQualifier
        cameraBottomTab: BottomTab,
        @GalleryQualifier
        galleryBottomTab: BottomTab
    ): BottomTabs =
        BottomTabs.Items(bottomTabs = listOf(paintBottomTab, cameraBottomTab, galleryBottomTab))

}