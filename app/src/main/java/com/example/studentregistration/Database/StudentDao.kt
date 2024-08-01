package com.example.studentregistration.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun enrollStudent(student : Student)

    @Query("delete from EnrolledStudentTable where rollNumber =:rollNumber" )
    suspend fun deregisterStudent(rollNumber : String)

    @Update
    suspend fun updateEnrolledStudent(student : Student)

    @Query("select * from EnrolledStudentTable")
    fun getAllEnrolledStudent():LiveData<List<Student>>
}

