package co.farooq.paginationcompose.ui.main_ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.farooq.paginationcompose.DefaultPaginator
import co.farooq.paginationcompose.data.model.Data
import co.farooq.paginationcompose.data.model.ListItem
import co.farooq.paginationcompose.data.repository.Repository
import kotlinx.coroutines.launch

class PaginationViewModel : ViewModel() {

    private val repository = Repository()

    var state by mutableStateOf(ScreenState())

    private val paginationLocal = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextKey ->
            repository.getItemsLocal(nextKey, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(itemsList = state.itemsList + items, page = newKey, endReached = items.isEmpty())
        }
    )

    private val paginationRemote = DefaultPaginator(
        initialKey = state.page,
        onLoadUpdated = {
            state = state.copy(isLoading = it)
        },
        onRequest = { nextKey ->
            repository.getItemsRemote(nextKey, 20)
        },
        getNextKey = {
            state.page + 1
        },
        onError = {
            state = state.copy(error = it?.localizedMessage)
        },
        onSuccess = { items, newKey ->
            state = state.copy(itemsRemoteList = state.itemsRemoteList + items, page = newKey, endReached = items.isEmpty())
        }
    )

    init {
        loadNextItems()
    }


    fun loadNextItems() {
        viewModelScope.launch {
            paginationRemote.loadNextItems()
//            paginationLocal.loadNextItems()
        }
    }
}


data class ScreenState(
    val isLoading: Boolean = false,
    val itemsList: List<ListItem> = emptyList(),
    val itemsRemoteList: List<Data> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)
