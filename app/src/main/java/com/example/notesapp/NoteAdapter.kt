package com.example.notesapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.SampleNotesBinding

class NoteAdapter(private val listener : RVListener) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    val list = ArrayList<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val sampleNotesBinding = SampleNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val view = ViewHolder(sampleNotesBinding)
        view.itemView.setOnClickListener {
            listener.onItemClicked(list[view.adapterPosition])
        }
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.sampleNotesBinding.noteView = model
        holder.sampleNotesBinding.executePendingBindings()
    }
    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Note>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    class ViewHolder(val sampleNotesBinding : SampleNotesBinding) : RecyclerView.ViewHolder(sampleNotesBinding.root)
}

