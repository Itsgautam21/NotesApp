package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Note::class], version = 3)

abstract class NoteDatabase : RoomDatabase()  {

    // To get the NoteDao reference
    abstract fun getNoteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        // if anything change in the table structure the migrate the function with new update query.
        private val migration_1_2 : Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //database.execSQL("ALTER TABLE notes_table ADD COLUMN title TEXT DEFAULT ''")
            }
        }
        fun getDatabase(context: Context): NoteDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext
                    ,NoteDatabase::class.java, "note_database")
                    .addMigrations(migration_1_2).build()
                INSTANCE = instance
                instance
            }
        }

    }
}