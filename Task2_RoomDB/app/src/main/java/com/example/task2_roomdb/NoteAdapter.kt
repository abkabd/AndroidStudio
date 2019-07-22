package com.example.task2_roomdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*
class NoteAdapter(private var noteList: List<Note>, private val listener: NoteListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note: Note = noteList[position]
        holder.note.text = note.note
        holder.btnDel.setOnClickListener {
            listener.onDeleteClick(note)
            notifyDataSetChanged()
        }

        holder.btnUpd.setOnClickListener {
            listener.onUpdateClick(note)
            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        if(noteList.isEmpty()) return 0
        return noteList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val note = itemView.textViewNote
        val btnDel = itemView.buttonDelete
        val btnUpd = itemView.buttonUpdate
    }


}

interface NoteListener {
    fun onDeleteClick(note: Note)
    fun onUpdateClick(note: Note)
}