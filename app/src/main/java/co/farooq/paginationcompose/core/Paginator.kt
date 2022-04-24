package co.farooq.paginationcompose.core

interface Paginator<Key,Item> {
    suspend fun loadNextItems()
    fun reset()
}