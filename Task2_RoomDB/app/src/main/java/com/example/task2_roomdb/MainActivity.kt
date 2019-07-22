package com.example.task2_roomdb

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_new_note.*
import kotlinx.android.synthetic.main.list_item.*
import java.text.FieldPosition

class MainActivity : AppCompatActivity(), NoteListener{


    private lateinit var noteDao: NoteDao
    private var noteDB: NoteRoomDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()

        buttonClear.setOnClickListener {
            noteDao.deleteAllNotes()
            Toast.makeText(
                applicationContext,
                "Database cleared",
                Toast.LENGTH_SHORT)
                .show()
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NewNoteActivity::class.java)
            startActivity(intent)
        }

    }

    private fun init() {
        noteDB = NoteRoomDatabase.getDatabase(applicationContext)!!
        noteDao = noteDB!!.noteDao()
        recyclerview.layoutManager = LinearLayoutManager(this)

        noteDao.getAllNotes().observe(this, object : Observer<List<Note>> {
            override fun onChanged(notes: List<Note>?) {
                recyclerview.adapter = NoteAdapter(notes!!, this@MainActivity)
            }
        })
    }

    override fun onDeleteClick(note: Note) {
        AsyncTask.execute {
            noteDao.delete(note)
        }

        Toast.makeText(
            applicationContext,
            "Deleted",
            Toast.LENGTH_SHORT)
            .show()
    }


    override fun onUpdateClick(note: Note) {
        note.note = "Done: " + note.note
        AsyncTask.execute{
            noteDao.update(note)
        }
        Toast.makeText(
            applicationContext,
            "Updated",
            Toast.LENGTH_SHORT)
            .show()
    }

}
