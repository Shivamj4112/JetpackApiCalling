package com.example.jetpackapicallingmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapicallingmvvm.ui.theme.JetpackApiCallingmvvmTheme
import com.example.jetpackapicallingmvvm.viewmodel.EmployeeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                EmployeeList(viewModel)
            }
        }
    }
}

@Composable
fun EmployeeCard(
    @DrawableRes profileImage: Int,
    name: String,
    age: String,
    salary: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .background(
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary
            ),
    ) {
        Image(
            painter = painterResource(id = profileImage),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(10.dp)
                .clip(shape = CircleShape)
                .size(46.dp),
        )
        Column(
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = age,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = salary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )

        }
    }
}

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            Modifier
                .padding(end = 8.dp)
                .size(40.dp))
        Text(
            text = "Loading...",
            fontSize = 18.sp,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun EmployeeList(viewModel: EmployeeViewModel) {

    LaunchedEffect(Unit) {
        viewModel.fetchEmployees()
    }

    val employee by viewModel.employees.observeAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp),
    ) {
        when (val data = employee?.data) {
            null -> LoadingIndicator(Modifier.align(Alignment.Center))

            else -> {

                if (data.any { it.employeeName.isBlank() || it.employeeAge == null || it.employeeSalary == null }) {
                    Text(
                        text = "Error: Invalid employee data!",
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    LazyColumn {
                        items(data, key = { it.id }) { item ->
                            EmployeeCard(
                                profileImage = R.drawable.ic_launcher_background,
                                name = item.employeeName,
                                age = item.employeeAge.toString(),
                                salary = item.employeeSalary.toString(),
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun EmployeeCardPreview() {

    JetpackApiCallingmvvmTheme {
        EmployeeCard(
            profileImage = R.drawable.ic_launcher_background,
            name = "John Doe",
            age = "30",
            salary = "5000",

            )
    }

}

@Preview(showBackground = true)
@Composable
fun LoadingIndicatorPreview() {
    JetpackApiCallingmvvmTheme {
        LoadingIndicator()
    }
}


@Preview(showBackground = true)
@Composable
fun EmployeeListPreview() {
    JetpackApiCallingmvvmTheme {
        EmployeeList(viewModel = EmployeeViewModel())
    }
}