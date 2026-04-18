package week14.Rajput991725720.var4.data



import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Vote::class], version = 3)
abstract class AppDb : RoomDatabase() {
    abstract fun voteDao(): VoteDao
}