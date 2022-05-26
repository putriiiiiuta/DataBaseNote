package putriiiiiuta.androidlima.databasenote.datastore


import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ManagerNote(context: Context) {
    private val dataStore : DataStore<Preferences> = context.createDataStore(name = "user_pref")
    companion object{
        val USERNAME = preferencesKey<String>("username")
        val BOOLEAN = preferencesKey<Boolean>("BOOLEAN")
    }

    suspend fun saveData(username:String){
        dataStore.edit {

            it[USERNAME]= username
        }
    }

    val userName : Flow<String> = dataStore.data.map {
        it[USERNAME] ?:""
    }

    val ceklogin : Flow<Boolean> = dataStore.data.map {
        it[BOOLEAN] ?: false
    }
    suspend fun hapusData(){
        dataStore.edit {
            it.clear()
        }

    }
    suspend fun setBoolean(boolean: Boolean){
        dataStore.edit {
            it[BOOLEAN] = boolean
        }
    }
}