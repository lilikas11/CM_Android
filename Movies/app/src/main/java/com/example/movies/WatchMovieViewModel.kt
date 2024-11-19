import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WatchListViewModel : ViewModel() {
    private val _watchList = MutableStateFlow(
        listOf(
            WatchItem(1, "Game of Thrones"),
            WatchItem(2, "Titanic"),
            WatchItem(3, "Avatar") ,
            WatchItem(4, "The office"),
            WatchItem(5, "Friends"),
            WatchItem(6, "Lost"),
            WatchItem(7, "Harry Potter")
        )
    )
    val watchList: StateFlow<List<WatchItem>> = _watchList

    fun addWatchItem(title: String) {
        val newItem = WatchItem(
            id = (_watchList.value.maxOfOrNull { it.id } ?: 0) + 1,
            title = title
        )
        _watchList.value = _watchList.value + newItem
    }

    fun toggleWatched(id: Int) {
        _watchList.value = _watchList.value.map {
            if (it.id == id) it.copy(isWatched = !it.isWatched) else it
        }
    }

    fun removeWatchItem(id: Int) {
        _watchList.value = _watchList.value.filter { it.id != id }
    }
}
