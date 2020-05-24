package com.stefankrstikj.skopjemovieschedule.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

class DiscoverViewModelFactory : NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val discoverViewModel = DiscoverViewModel()
        return discoverViewModel as T
    }
}