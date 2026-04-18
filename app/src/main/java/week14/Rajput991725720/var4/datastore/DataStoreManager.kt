package week14.Rajput991725720.var4.datastore


import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    private val Context.dataStore by preferencesDataStore("vote_store")

    private val NONE_KEY = intPreferencesKey("none_count")

    suspend fun incrementNone() {
        context.dataStore.edit {
            val current = it[NONE_KEY] ?: 0
            it[NONE_KEY] = current + 1
        }
    }

    suspend fun decrementNone() {
        context.dataStore.edit {
            val current = it[NONE_KEY] ?: 0
            if (current > 0) {
                it[NONE_KEY] = current - 1
            }
        }
    }

    val noneCount: Flow<Int> = context.dataStore.data.map {
        it[NONE_KEY] ?: 0
    }

    suspend fun clear() {
        context.dataStore.edit { it.clear() }
    }
}