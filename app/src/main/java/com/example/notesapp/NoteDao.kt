package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note : Note)
    @Delete
    fun delete(note : Note)
    @Update
    fun update(note: Note)

    @Query("SELECT * FROM NOTES_TABLE ORDER BY id ASC")
    fun getAllNotes() : LiveData<List<Note>>
}