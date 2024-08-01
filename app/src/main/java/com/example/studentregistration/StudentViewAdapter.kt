package com.example.studentregistration

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentregistration.Database.Student
import java.util.zip.Inflater

class StudentViewAdapter() : RecyclerView.Adapter<StudentViewAdapter.StudentViewHolder>() {

    private val studentList = ArrayList<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_item,parent,false)
        return StudentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }

    fun setStudentList(studentList : List<Student>){
        this.studentList.clear()
        this.studentList.addAll(studentList)
    }

    class StudentViewHolder(private var view : View) : RecyclerView.ViewHolder(view){
        private var name: TextView = view.findViewById<TextView>(R.id.studentNameId)
        private var emailId: TextView = view.findViewById<TextView>(R.id.studentEmailId)
        private var rollNumber: TextView =view.findViewById<TextView>(R.id.studentEnrollNumID)
        fun bind(student : Student){
            name.text=student.name
            emailId.text=student.emailId
            rollNumber.text=student.rollNumber
        }
    }
}

