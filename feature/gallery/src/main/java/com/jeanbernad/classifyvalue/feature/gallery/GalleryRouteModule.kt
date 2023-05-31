package com.jeanbernad.classifyvalue.feature.gallery

import com.jeanbernad.classifyvalue.core.featureApi.FeatureApi
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTab
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class GalleryRouteModule {

    @Provides
    @GalleryQualifier
    @ActivityRetainedScoped
    fun provideGalleryBottomTab(): BottomTab = GalleryBottomTab()

    @Provides
    @GalleryQualifier
    @ActivityRetainedScoped
    fun provideGalleryFeatureApi(): FeatureApi = GalleryFeatureImpl()

}