package com.cuty.simplenotepad

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@PrimaryKey

           var word: String,
           var fecha: String)