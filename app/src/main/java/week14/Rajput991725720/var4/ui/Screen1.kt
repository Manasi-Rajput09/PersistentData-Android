package week14.Rajput991725720.var4.ui
//Manasi Rajput
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border

import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import week14.Rajput991725720.var4.data.VoteDao
import week14.Rajput991725720.var4.datastore.DataStoreManager
import week14.Rajput991725720.var4.navigation.Screen

import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun Screen1(nav: NavController, store: DataStoreManager, dao: VoteDao) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var idInput by rememberSaveable { mutableStateOf("") }
    var nameInput by rememberSaveable { mutableStateOf("") }
    var ageInput by rememberSaveable { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("REGISTRATION", style = MaterialTheme.typography.headlineLarge)

        Spacer(Modifier.height(8.dp))
        Text("Enter your data:", color = Purple)
        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier.border(2.dp, Color.Black).padding(16.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val textFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Red,
                    unfocusedTextColor = Color.Red
                )

                OutlinedTextField(
                    value = idInput,
                    onValueChange = { idInput = it },
                    label = { Text("ID") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = textFieldColors
                )
                
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = nameInput,
                    onValueChange = { nameInput = it },
                    label = { Text("Name") },
                    colors = textFieldColors
                )
                
                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = ageInput,
                    onValueChange = { ageInput = it },
                    label = { Text("Age") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = textFieldColors
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                idInput = ""
                nameInput = ""
                ageInput = ""
                CoroutineScope(Dispatchers.IO).launch {
                    store.clear()
                }
            }) {
                Text("Clear", color = Color.White)
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Purple),
                onClick = {
                    if (idInput.isNotBlank() && nameInput.isNotBlank() && ageInput.isNotBlank()) {
                        coroutineScope.launch {
                            val existingVote = dao.getVoteById(idInput)
                            if (existingVote != null) {
                                kotlinx.coroutines.withContext(Dispatchers.Main) {
                                    Toast.makeText(context, "enter a unique id", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                kotlinx.coroutines.withContext(Dispatchers.Main) {
                                    nav.navigate(Screen.Screen2.createRoute(idInput, nameInput, ageInput))
                                }
                            }
                        }
                    }
                }
            ) {
                Text("Next", color = Color.White)
            }
        }
    }
}