package com.example.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// переделать
@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)