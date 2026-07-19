package org.example.inmyword.cache

import org.example.inmyword.entity.BibleBook
import org.example.inmyword.entity.User
import org.example.inmyword.entity.UserProgress
import kotlin.time.Instant

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllUsers(): List<User> {
        return dbQuery.selectAllUsers(::mapUserSelecting).executeAsList()
    }

    internal fun clearAndCreateUsers(users : List<User>) {
        dbQuery.transaction {
            dbQuery.removeAllUsers()
            users.forEach { user ->
                dbQuery.insertUser(
                    id = user.id,
                    name = user.name,
                    email = user.email,
                )
            }
        }
    }

    internal fun getAllBibleBooks(): List<BibleBook> {
        return dbQuery.selectAllBibleBooks(::mapBibleBookSelecting).executeAsList()
    }

    internal fun clearAndCreateBibleBooks(bibleBooks: List<BibleBook>) {
        dbQuery.transaction {
            dbQuery.removeAllBibleBooks()
            bibleBooks.forEach { bibleBook ->
                dbQuery.insertBibleBook(
                    id = bibleBook.id,
                    book_name = bibleBook.bookName,
                    book_chapters = bibleBook.bookChapters
                )
            }
        }
    }

    internal fun getAllUserProgress(): List<UserProgress> {
        return dbQuery.selectAllUserProgress(::mapUserProgressSelecting).executeAsList()
    }

    internal fun clearAndCreateUserProgress(usersProgress: List<UserProgress>){
        dbQuery.transaction{
            dbQuery.removeAllUserProgress()
            usersProgress.forEach { userProgress ->
                dbQuery.insertUserProgress(
                    id = userProgress.id,
                    user_id = userProgress.userId,
                    bible_book_id = userProgress.bibleBookId,
                    bible_chapter = userProgress.bibleChapter,
                    completed = userProgress.completed
                )
            }
        }
    }

    private fun mapBibleBookSelecting(
        id: Int,
        bookName: String,
        bookChapters: Int
    ) : BibleBook {
        return BibleBook(id, bookName, bookChapters)
    }

    private fun mapUserSelecting(
        id: Int,
        name: String,
        email: String
    ) : User {
        return User(id, name, email)
    }

    private fun mapUserProgressSelecting(
        id: Int,
        userId: Int,
        bibleBookId: Int,
        bibleChapter: Int,
        completed: Instant
    ) : UserProgress {
        return UserProgress(id, userId, bibleBookId, bibleChapter, completed)
    }

}