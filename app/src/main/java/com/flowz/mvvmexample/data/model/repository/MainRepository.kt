package com.flowz.mvvmexample.data.model.repository

import com.flowz.mvvmexample.data.model.User
import com.flowz.mvvmexample.data.model.api.ApiHelper
import io.reactivex.Single

class MainRepository (private val apiHelper: ApiHelper){

    fun getUsers(): Single<List<User>>{
        return apiHelper.getUsers()
    }
}