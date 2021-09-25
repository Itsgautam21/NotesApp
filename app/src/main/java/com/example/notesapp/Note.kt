package com.example.notesapp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.IgnoredOnParcel

// Note class as Model represented Table's Structure of DataBase.
@kotlinx.parcelize.Parcelize
@Entity(tableName = "notes_table") // Table Name
data class Note(@ColumnInfo(name = "text") val text : String, @ColumnInfo(name = "title") val title : String?) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}