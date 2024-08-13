package com.example.jetpackapicallingmvvm.model.repository

import com.example.jetpackapicallingmvvm.model.Employee
import com.example.jetpackapicallingmvvm.model.api.RetrofitInstance

class ApiRepository {

    private val employeeService = RetrofitInstance.getEmployeeService

    suspend fun getEmployee() : Employee {
        return employeeService.getEmployee()
    }
}