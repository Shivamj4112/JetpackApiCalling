package com.example.jetpackapicallingmvvm.model

import com.google.gson.annotations.SerializedName

data class Employee(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("profile_image")
	val profileImage: String,

	@field:SerializedName("employee_name")
	val employeeName: String,

	@field:SerializedName("employee_salary")
	val employeeSalary: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("employee_age")
	val employeeAge: Int
)
