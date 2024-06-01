package com.example.jetpackapicallingmvvm.model.api

import com.example.jetpackapicallingmvvm.model.Employee
import retrofit2.http.GET

interface GetEmployeeService {
    @GET("employees")
    suspend fun getEmployee() : Employee
}