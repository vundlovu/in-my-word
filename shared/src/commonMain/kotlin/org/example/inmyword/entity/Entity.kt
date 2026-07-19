package org.example.inmyword.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("email")
    val email: String,
)

@Serializable
data class UserProgress(
    @SerialName("id")
    val id: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("bible_book_id")
    val bibleBookId: Int,
    @SerialName("bible_chapter")
    val bibleChapter: Int,
    @SerialName("completed")
    val completed: Instant
)
@Serializable
data class BibleBook(
    @SerialName("id")
    val id: Int,
    @SerialName("book_name")
    val bookName: String,
    @SerialName("book_chapters")
    val bookChapters: Int
)