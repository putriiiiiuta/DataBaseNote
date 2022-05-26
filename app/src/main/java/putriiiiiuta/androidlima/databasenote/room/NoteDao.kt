package putriiiiiuta.androidlima.databasenote.room


import androidx.room.*

@Dao
interface NoteDao {
    @Insert
    fun addNote(note: Note):Long
    @Query("SELECT * FROM Note")
    fun getNote(): List<Note>
    @Delete
    fun deleteNote(note: Note):Int
    @Update
    fun updateNote(note: Note): Int


    @Insert
    fun registerUser(user: User):Long
    @Query("SELECT * FROM User WHERE User.username = :username")
    fun getUserRegistered(username:String): User
}