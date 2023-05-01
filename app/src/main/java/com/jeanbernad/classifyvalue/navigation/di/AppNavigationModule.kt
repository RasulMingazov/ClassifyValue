package com.jeanbernad.classifyvalue.navigation.di

import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTab
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTabs
import com.jeanbernad.classifyvalue.feature.camera.CameraQualifier
import com.jeanbernad.classifyvalue.feature.paint.PaintQualifier
import com.jeanbernad.classifyvalue.navigation.AppFeatures
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class AppNavigationModule {

    @Provides
    fun provideAppNavigationFeatures(
        @PaintQualifier
        paintFeatureApi: FeatureApi,
        @CameraQualifier
        cameraFeatureApi: FeatureApi
    ): AppFeatures =
        AppFeatures.Features(features = listOf(paintFeatureApi, cameraFeatureApi))

    @Provides
    fun provideAppBottomTabs(
        @PaintQualifier
        paintBottomTab: BottomTab,
        @CameraQualifier
        cameraBottomTab: BottomTab,
    ): BottomTabs =
        BottomTabs.Items(bottomTabs = listOf(paintBottomTab, cameraBottomTab))

}