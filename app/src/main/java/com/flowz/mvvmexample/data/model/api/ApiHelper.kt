package com.flowz.mvvmexample.data.model.api

class ApiHelper(private val apiService: ApiService) {

    fun getUsers() = apiService.getUsers()
}