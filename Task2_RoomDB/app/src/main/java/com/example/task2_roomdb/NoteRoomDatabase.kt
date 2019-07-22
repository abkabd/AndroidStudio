package com.example.task2_roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)

abstract class NoteRoomDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        private var instance : NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase? {
            if (instance == null) {
                synchronized(NoteRoomDatabase::class)
                {
                    instance = Room.databaseBuilder(
                        context.getApplicationContext(), NoteRoomDatabase::class.java, "note.db"
                    ).build()
                }
            }
            return instance
        }

    }

}

