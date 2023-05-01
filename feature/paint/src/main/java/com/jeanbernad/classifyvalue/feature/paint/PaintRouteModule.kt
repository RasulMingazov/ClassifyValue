package com.jeanbernad.classifyvalue.feature.paint

import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTab
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class PaintRouteModule {

    @Provides
    @PaintQualifier
    @ActivityRetainedScoped
    fun providePaintBottomTab(): BottomTab = PaintBottomTab()

    @Provides
    @PaintQualifier
    @ActivityRetainedScoped
    fun providePaintFeatureApi(): FeatureApi = PaintFeatureImpl()

}