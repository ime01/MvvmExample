package com.flowz.mvvmexample.display.main.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flowz.mvvmexample.data.model.api.ApiHelper
import com.flowz.mvvmexample.data.model.repository.MainRepository
import com.flowz.mvvmexample.display.main.view.viewmodel.MainViewModel

class ViewModelFactory (private val apiHelper: ApiHelper): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }

        throw IllegalAccessException("Unknown class name")

    }
}