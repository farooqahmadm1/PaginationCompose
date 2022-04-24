package co.farooq.paginationcompose.data.data_source.local

import co.farooq.paginationcompose.data.model.ListItem

object LocalSource {

    val remoteDataSource = (1..100).map {
        ListItem(
            title = "Item $it",
            description = "Description $it"
        )
    }
}