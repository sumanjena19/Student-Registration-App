package com.example.studentregistration

import com.example.studentregistration.Database.CollegeDatabase
import com.example.studentregistration.Database.Student
import com.example.studentregistration.Database.StudentDao
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var studentDao : StudentDao
    private lateinit var name : TextInputEditText
    private lateinit var emailId : TextInputEditText
    private lateinit var rollNumber : TextInputEditText
    private lateinit var viewModel: StudentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var registerBtn : Button
    private lateinit var deregisterBtn : Button
    private var isSelected : Boolean = false
    private var updateString : String = "UPDATE"
    private var deregisterString : String = "DEREGISTER"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        studentDao = CollegeDatabase.getDataBaseInstance(application).getStudentDao()
        name=findViewById(R.id.nameId)
        emailId=findViewById(R.id.emailId)
        rollNumber=findViewById(R.id.enrollmentId)
        registerBtn=findViewById(R.id.registerBtn)
        deregisterBtn=findViewById(R.id.clearBtn)
        recyclerView=findViewById(R.id.studentListViewId)

        val studentViewModelFactory= StudentViewModelFactory(studentDao)
        viewModel=ViewModelProvider(this, studentViewModelFactory)[StudentViewModel::class.java]

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter = StudentViewAdapter { student: Student ->
            studentItemClicked(student)
        }
        recyclerView.adapter=adapter

        viewModel.enrolledStudentList.observe(this, Observer {
             adapter.setStudentList(it)
             adapter.notifyDataSetChanged()
        })

        registerBtn.setOnClickListener {
            register(Student(rollNumber.text.toString(),name.text.toString(), emailId.text.toString()))
            clear()
        }

        deregisterBtn.setOnClickListener {
            deregister(Student(rollNumber.text.toString(),name.text.toString(), emailId.text.toString()))
            clear()
        }

    }
    private fun register(student: Student){
        viewModel.enrollStudent(student)
        if(isSelected){
            registerBtn.setText(updateString)
            deregisterBtn.setText(deregisterString)
            isSelected=false
        }
    }
    private fun deregister(student: Student){
        viewModel.deregisterStudent(student)
        registerBtn.setText(updateString)
        deregisterBtn.setText(deregisterString)
        isSelected=false
    }
    private fun clear(){
        name.text=null
        rollNumber.text=null
        emailId.text=null
    }
    private fun studentItemClicked(student : Student){
            registerBtn.setText(updateString)
            deregisterBtn.setText(deregisterString)
            isSelected=true
            name.setText(student.name)
            emailId.setText(student.emailId)
            rollNumber.setText(student.rollNumber)
    }
}