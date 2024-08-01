package com.example.studentregistration.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("EnrolledStudentTable")
data class Student(
    @PrimaryKey
    var rollNumber : String,
    var name:String,
    var emailId : String
)
