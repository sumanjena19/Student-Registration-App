package com.example.studentregistration

import com.example.studentregistration.Database.StudentDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class StudentViewModelFactory(private val studentDao : StudentDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StudentViewModel::class.java)){
            return StudentViewModel(studentDao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}