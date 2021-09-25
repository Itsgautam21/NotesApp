package com.example.notesapp

interface RVListener {
    fun onItemClicked(note: Note)
    fun onItemLongClicked(note: Note)
}