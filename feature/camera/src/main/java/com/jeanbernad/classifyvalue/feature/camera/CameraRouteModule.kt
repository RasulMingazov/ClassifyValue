package com.jeanbernad.classifyvalue.feature.camera

import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTab
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class CameraRouteModule {

    @Provides
    @CameraQualifier
    @ActivityRetainedScoped
    fun provideCameraBottomTab(): BottomTab = CameraBottomTab()

    @Provides
    @CameraQualifier
    @ActivityRetainedScoped
    fun provideCameraFeatureApi(): FeatureApi = CameraFeatureImpl()

}