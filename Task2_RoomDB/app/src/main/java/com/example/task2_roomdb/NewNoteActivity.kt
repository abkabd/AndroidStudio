package com.example.task2_roomdb

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        buttonAdd.setOnClickListener {
            val noteDB = NoteRoomDatabase.getDatabase(applicationContext)!!

            if(editText.text.isNotEmpty() && buttonAdd.text == "Add"){
                AsyncTask.execute {
                    val note = Note(editText.text.toString())
                    noteDB.noteDao().insert(note)
                }

                Toast.makeText(
                    applicationContext,
                    "Saved",
                    Toast.LENGTH_LONG)
                    .show()

                finish()
            }
            else{
                Toast.makeText(
                    applicationContext,
                    "Empty text cannot be saved",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        buttonCancel.setOnClickListener {
            finish()
        }


    }
}
