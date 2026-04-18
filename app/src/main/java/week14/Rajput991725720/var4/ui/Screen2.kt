package week14.Rajput991725720.var4.ui
//Manasi Rajput

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.foundation.border
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import week14.Rajput991725720.var4.data.Vote
import week14.Rajput991725720.var4.data.VoteDao
import week14.Rajput991725720.var4.datastore.DataStoreManager
import week14.Rajput991725720.var4.navigation.Screen

import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun Screen2(nav: NavController, dao: VoteDao, store: DataStoreManager, id: String, name: String, age: String) {
    val context = LocalContext.current
    var selected by rememberSaveable { mutableStateOf("") }
    var savedOption by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text("VOTING STATION", color = Color.Black, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Black)) {
                    append("Hello ")
                }
                withStyle(style = SpanStyle(color = Color.Red)) {
                    append(name)
                }
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(16.dp))

        Text("Select one party that you are voting for", color = Purple, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier.border(2.dp, Color.Blue).padding(16.dp)) {
            Column {
                val parties = listOf(
                    Triple("Red Party", "Red Party", Color.Red),
                    Triple("Blue Party", "Blue Party", Color.Blue),
                    Triple("Black Party", "Black Party", Color.Black),
                    Triple("none", "none", Color.Green)
                )

                parties.forEach { (label, value, color) ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Switch(
                            checked = selected == value,
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    selected = value
                                } else if (selected == value) {
                                    selected = ""
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.Green,
                                checkedTrackColor = Color.Green.copy(alpha = 0.5f)
                            )
                        )
                        Spacer(Modifier.width(12.dp))
                        Text(label, color = color)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { nav.popBackStack() }) {
                Text("Back", color = Color.White)
            }

            Button(
                enabled = selected.isNotBlank() && selected != savedOption,
                onClick = {
                    if (selected.isNotBlank()) {
                        savedOption = selected
                        CoroutineScope(Dispatchers.IO).launch {
                            val existing = dao.getVoteById(id)
                            
                            // To be perfectly safe, if they somehow clicked concurrently
                            if (existing?.option == selected) {
                                return@launch
                            }

                            dao.insert(Vote(enteredId = id, name = name, age = age, option = selected))
                            
                            // Only increment if they are newly voting for 'none' to avoid duplicate counts!
                            if (selected == "none" && existing?.option != "none") {
                                store.incrementNone()
                            } else if (selected != "none" && existing?.option == "none") {
                                // Decrement if they change their mind away from none!
                                store.decrementNone()
                            }

                            kotlinx.coroutines.withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Saved successfully!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            ) {
                Text("Save", color = Color.White)
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Purple),
                onClick = { nav.navigate(Screen.Screen3.route) }
            ) {
                Text("Next", color = Color.White)
            }
        }
    }
}