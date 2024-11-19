import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
@Composable
fun WatchListApp(viewModel: WatchListViewModel = viewModel()) {
    val watchList by viewModel.watchList.collectAsState()

    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "My Watch List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(watchList.size) { index ->
                val item = watchList[index]
                WatchItemRow(
                    item = item,
                    onToggleWatched = { viewModel.toggleWatched(item.id) },
                    onDelete = { viewModel.removeWatchItem(item.id) }
                )
            }
        }
    }

    if (showDialog) {
        AddItemDialog(
            onDismiss = { showDialog = false },
            onAdd = { title ->
                viewModel.addWatchItem(title)
                showDialog = false
            }
        )
    }
}


