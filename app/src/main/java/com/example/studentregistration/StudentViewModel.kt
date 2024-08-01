package com.example.studentregistration

import androidx.lifecycle.LiveData
import com.example.studentregistration.Database.Student
import com.example.studentregistration.Database.StudentDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentViewModel(private val studentDao : StudentDao) : ViewModel() {

    val enrolledStudentList= studentDao.getAllEnrolledStudent()

    fun enrollStudent(student : Student) = viewModelScope.launch{
        studentDao.enrollStudent(student)
    }

    fun deregisterStudent(student : Student)= viewModelScope.launch {
        studentDao.deregisterStudent(student.rollNumber)
    }

}