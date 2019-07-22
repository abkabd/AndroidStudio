package com.example.task2_roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAllNotes()

    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    fun update(note: Note)


    /*
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    */
}
