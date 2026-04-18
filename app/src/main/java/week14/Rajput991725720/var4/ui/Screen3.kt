package week14.Rajput991725720.var4.ui
//Manasi Rajput
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import week14.Rajput991725720.var4.data.VoteDao
import week14.Rajput991725720.var4.datastore.DataStoreManager
import androidx.navigation.NavController


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.border
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun Screen3(nav: NavController, dao: VoteDao, store: DataStoreManager) {

    val votes by dao.getAllVotes().collectAsState(initial = emptyList())
    val noneCount by store.noneCount.collectAsState(initial = 0)

    var showDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("VOTING DB", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier.fillMaxWidth().border(2.dp, Color.Green).padding(8.dp)) {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("ID", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("Name", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("Age", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text("Voted For", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                }
                Spacer(Modifier.height(8.dp))
                LazyColumn(modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)) {
                    items(votes) { vote ->
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(vote.enteredId, modifier = Modifier.weight(1f))
                            Text(vote.name, modifier = Modifier.weight(1f))
                            Text(vote.age, modifier = Modifier.weight(1f))
                            Text(vote.option, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        Text("Number of votes that have not supported any party so far:", color = Color.Blue)
        Text("$noneCount", color = Color.Red, style = MaterialTheme.typography.titleLarge)

        Spacer(Modifier.height(20.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Purple, contentColor = Color.White),
                onClick = { nav.popBackStack() }
            ) {
                Text("Back")
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Purple, contentColor = Color.White),
                onClick = { showDialog = true }
            ) {
                Text("Exit")
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(onClick = {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }) {
                    Text("Exit")
                }
            },
            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Continue")
                }
            },
            title = { Text("Exit App") },
            text = { Text("Do you want to exit?") }
        )
    }
}