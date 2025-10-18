package com.example.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM posts WHERE userId = :userId")
    suspend fun getPostsByUserId(userId: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertPost(post: PostEntity)

    @Delete
    suspend fun deletePost(post: PostEntity)
}
