package week14.Rajput991725720.var4.ui
//Manasi Rajput
import androidx.compose.foundation.background
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
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "VOTING DATABASE",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                // Table Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "ID",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(0.6f),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        "Name",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1.2f),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        "Age",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(0.6f),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        "Voted For",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1.2f),
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                
                HorizontalDivider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(votes) { vote ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(vote.enteredId, modifier = Modifier.weight(0.6f))
                            Text(vote.name, modifier = Modifier.weight(1.2f))
                            Text(vote.age, modifier = Modifier.weight(0.6f))
                            Text(vote.option, modifier = Modifier.weight(1.2f), color = MaterialTheme.colorScheme.primary)
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 8.dp),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.secondaryContainer,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Votes for No Party:",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                "$noneCount",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.outline
                ),
                onClick = { nav.popBackStack() }
            ) {
                Text("Back", color = Color.White)
            }

            Button(
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                ),
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
                TextButton(onClick = {
                    android.os.Process.killProcess(android.os.Process.myPid())
                }) {
                    Text("Exit", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Continue")
                }
            },
            title = { Text("Exit Application") },
            text = { Text("Are you sure you want to exit the voting system?") }
        )
    }
}
