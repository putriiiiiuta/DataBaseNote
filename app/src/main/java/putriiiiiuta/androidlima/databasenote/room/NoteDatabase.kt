package putriiiiiuta.androidlima.databasenote.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class, User::class], version = 1)
abstract class NoteDatabase  : RoomDatabase() {
    abstract fun notedao():NoteDao

    companion object{
        private var INSTANCE: NoteDatabase? = null

        fun getinstance(context: Context): NoteDatabase? {
            if(INSTANCE == null){
                synchronized(NoteDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "App.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}