package com.example.jetpackapicallingmvvm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackapicallingmvvm.model.Employee
import com.example.jetpackapicallingmvvm.model.repository.ApiRepository
import kotlinx.coroutines.launch

class EmployeeViewModel : ViewModel(){

    private val apiRepository = ApiRepository()

    private val _employees = MutableLiveData<Employee>()
    val employees : LiveData<Employee> = _employees

    fun fetchEmployees() {

        viewModelScope.launch {
            try {
                val empl = apiRepository.getEmployee()
                _employees.value = empl
            } catch (e: Exception) {
                Log.d("Tag", "fetchEmployees: ${e.message}")
            }
        }

    }
}