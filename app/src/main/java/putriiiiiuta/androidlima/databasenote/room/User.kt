package putriiiiiuta.androidlima.databasenote.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey var username :String,
    @ColumnInfo(name = "nama") var nama: String,
    @ColumnInfo(name = "password") var password: String
): Parcelable
