package com.jeanbernad.classifyvalue.feature.digit.data

import android.content.Context
import com.jeanbernad.classifyvalue.feature.digit.domain.RecognizeDigitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
class RecognizeDigitDataModule {

    @Provides
    @ActivityRetainedScoped
    fun provideRecognizeDigit(@ApplicationContext context: Context): RecognizeDigit = RecognizeDigit.Base(context)

    @Provides
    @ActivityRetainedScoped
    fun provideRecognizeDigitSource(api: RecognizeDigit): RecognizeDigitSource =
        RecognizeDigitSource.Base(api)

    @Provides
    @ActivityRetainedScoped
    fun provideRecognizeDigitRepository(source: RecognizeDigitSource): RecognizeDigitRepository =
        BaseRecognizeDigitRepository(source)
}