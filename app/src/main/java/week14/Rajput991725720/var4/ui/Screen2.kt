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
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            "VOTING STATION",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(8.dp))
        
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("Hello ")
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                    append(name)
                }
            },
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(16.dp))

        Text(
            "Select one party that you are voting for",
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                val parties = listOf(
                    Triple("Red Party", "Red Party", Color(0xFFD32F2F)),
                    Triple("Blue Party", "Blue Party", Color(0xFF1976D2)),
                    Triple("Black Party", "Black Party", Color(0xFF212121)),
                    Triple("None", "none", Color(0xFF388E3C))
                )

                parties.forEach { (label, value, color) ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
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
                                checkedThumbColor = MaterialTheme.colorScheme.primary,
                                checkedTrackColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                uncheckedThumbColor = MaterialTheme.colorScheme.outline,
                                uncheckedTrackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            label,
                            color = color,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.outline,
                    contentColor = Color.White
                ),
                onClick = { nav.popBackStack() }
            ) {
                Text("Back")
            }

            Button(
                modifier = Modifier.weight(1f),
                enabled = selected.isNotBlank() && selected != savedOption,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                onClick = {
                    if (selected.isNotBlank()) {
                        savedOption = selected
                        CoroutineScope(Dispatchers.IO).launch {
                            val existing = dao.getVoteById(id)
                            
                            if (existing?.option == selected) {
                                return@launch
                            }

                            dao.insert(Vote(enteredId = id, name = name, age = age, option = selected))
                            
                            if (selected == "none" && existing?.option != "none") {
                                store.incrementNone()
                            } else if (selected != "none" && existing?.option == "none") {
                                store.decrementNone()
                            }

                            kotlinx.coroutines.withContext(Dispatchers.Main) {
                                Toast.makeText(context, "Saved successfully!", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            ) {
                Text("Save")
            }

            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { nav.navigate(Screen.Screen3.route) }
            ) {
                Text("Next")
            }
        }
    }
}