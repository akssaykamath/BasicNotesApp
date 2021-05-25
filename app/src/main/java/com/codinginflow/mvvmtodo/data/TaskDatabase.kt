package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){

    abstract fun taskDao() : TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()
            applicationScope.launch {
                dao.insert(Task("Wash the dishes"))
                dao.insert(Task("Go to mama", completed = true))
                dao.insert(Task("Do IIT", completed = true))
                dao.insert(Task("IGNOU assignments", important = true))
                dao.insert(Task("Bajaj Loan"))
                dao.insert(Task("Find rental houses", important = true))
                dao.insert(Task("Check tickertape.com"))
                dao.insert(Task("Get up early tomorrow"))
                dao.insert(Task("Apply jobs"))
                dao.insert(Task("Check the job given by Sandra", important = true))

            }
        }
    }
}