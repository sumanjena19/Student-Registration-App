package com.example.studentregistration.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class CollegeDatabase : RoomDatabase() {
    abstract fun getStudentDao() : StudentDao

    /*
    * Singleton object of database class through out the application
    */

    companion object{
        private var INSTANCE : CollegeDatabase?= null

        fun getDataBaseInstance(context : Context) : CollegeDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance= Room.databaseBuilder(
                        context.applicationContext,
                        CollegeDatabase::class.java,
                        "College Database"
                    ).build()
                }
                return instance
            }
        }
    }
}