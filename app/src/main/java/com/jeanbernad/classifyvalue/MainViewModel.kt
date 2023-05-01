package com.jeanbernad.classifyvalue

import com.jeanbernad.classifyvalue.core.presentation.BaseViewModel
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTabs
import com.jeanbernad.classifyvalue.navigation.AppFeatures
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


interface MainViewModel {

    fun navigationFeatures(): StateFlow<AppFeatures>

    fun bottomTabs(): StateFlow<BottomTabs>

    abstract class Base(features: AppFeatures, bottomTabs: BottomTabs) : BaseViewModel(),
        MainViewModel {

        private val navigationFeatures: MutableStateFlow<AppFeatures> =
            MutableStateFlow(features)

        private val bottomTabs: MutableStateFlow<BottomTabs> =
            MutableStateFlow(bottomTabs)

        override fun navigationFeatures() = navigationFeatures.asStateFlow()

        override fun bottomTabs() = bottomTabs.asStateFlow()

    }

    @HiltViewModel
    class Item @Inject constructor(
        features: AppFeatures, bottomTabs: BottomTabs
    ) : Base(features, bottomTabs)
}