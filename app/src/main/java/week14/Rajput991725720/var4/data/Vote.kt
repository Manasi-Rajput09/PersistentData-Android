package week14.Rajput991725720.var4.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(indices = [androidx.room.Index(value = ["enteredId"], unique = true)])
data class Vote(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int = 0,
    val enteredId: String,
    val name: String,
    val age: String,
    val option: String
)