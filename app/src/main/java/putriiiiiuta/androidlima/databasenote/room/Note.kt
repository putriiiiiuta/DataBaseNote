package putriiiiiuta.androidlima.databasenote.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "judul") val judul : String,
    @ColumnInfo(name = "waktu") val waktu : String,
    @ColumnInfo(name = "isi") val isi : String
): Parcelable
