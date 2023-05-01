package com.jeanbernad.classifyvalue

import com.jeanbernad.classifyvalue.core.presentation.BaseViewModel
import com.jeanbernad.classifyvalue.core.presentation.bottom.BottomTabs
import com.jeanbernad.classifyvalue.core.presentation.navigation.NavigationFeatures
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


interface MainViewModel {

    fun navigationFeatures(): StateFlow<NavigationFeatures>

    fun bottomTabs(): StateFlow<BottomTabs>

    abstract class Base(features: NavigationFeatures, bottomTabs: BottomTabs) : BaseViewModel(),
        MainViewModel {

        private val navigationFeatures: MutableStateFlow<NavigationFeatures> =
            MutableStateFlow(features)

        private val bottomTabs: MutableStateFlow<BottomTabs> =
            MutableStateFlow(bottomTabs)

        override fun navigationFeatures() = navigationFeatures.asStateFlow()

        override fun bottomTabs() = bottomTabs.asStateFlow()

    }

    @HiltViewModel
    class Item @Inject constructor(
        features: NavigationFeatures, bottomTabs: BottomTabs
    ) : Base(features, bottomTabs)
}