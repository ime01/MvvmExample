package com.flowz.mvvmexample.data.model.api

import com.flowz.mvvmexample.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}