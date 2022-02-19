package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), RVListener {

    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter( this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application))[NoteViewModel::class.java]
        viewModel.allNotes.observe(this, { list ->
            list?.let { adapter.updateList(it) }
        })

        val item = object : SwipeToDelete(this, 0, ItemTouchHelper.LEFT) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.delete(adapter.list[viewHolder.adapterPosition])
                //adapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(item)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(applicationContext, NoteEditor::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(applicationContext, NoteEditor::class.java)
        intent.putExtra("update", note)
        intent.putExtra("id", note.id.toString())
        startActivity(intent)
    }

    override fun onItemLongClicked(note: Note) {
        viewModel.delete(note)
    }
}