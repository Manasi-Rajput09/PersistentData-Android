package week14.Rajput991725720.var4.data



import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import androidx.room.OnConflictStrategy

@Dao
interface VoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vote: Vote)

    @Query("SELECT * FROM Vote WHERE enteredId = :id LIMIT 1")
    suspend fun getVoteById(id: String): Vote?

    @Query("SELECT * FROM Vote")
    fun getAllVotes(): Flow<List<Vote>>
}