package com.example.notesapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val listener : RVListener)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val list = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_notes, parent,
            false))
        view.itemView.setOnClickListener {
            listener.onItemClicked(list[view.adapterPosition])
        }
        view.title.setOnLongClickListener {
            listener.onItemLongClicked(list[view.adapterPosition])
            true
        }
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.notes.text = model.text
        holder.title.text = model.title
    }
    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val notes: TextView = itemView.findViewById(R.id.notes)
    }
}

