package com.example.notesapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout

class NoteEditor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val titleText = findViewById<TextInputLayout>(R.id.titleEdit)
        val notesEdit = findViewById<TextInputLayout>(R.id.notesEdit)
        val submit = findViewById<ImageView>(R.id.submit)
        val back = findViewById<ImageView>(R.id.back)

        val lss : Note? = intent.getParcelableExtra("update")
        val id : String? = intent.getStringExtra("id")
        // Set the Text Value if it is coming from the old notes.
        lss?.let {
            titleText.editText?.setText(it.title)
            notesEdit.editText?.setText(it.text)
        }
        submit.setOnClickListener {
            val text = notesEdit.editText?.text.toString().trim()
            val title = titleText.editText?.text.toString().trim()
            if (lss != null) {
                if (text.isNotEmpty()) { // Update the Note.
                    val updateNote = Note(text, title)
                    id?.let { updateNote.id = it.toInt() }
                    viewModel.update(updateNote)
                }
            }
            // Insert the new Note.
            else if (text.isNotEmpty()) viewModel.insert(Note(text, title))
            finish()
        }
        back.setOnClickListener {
            finish()
        }
    }
}