package co.farooq.paginationcompose.data.repository

import co.farooq.paginationcompose.data.model.ListItem
import co.farooq.paginationcompose.data.data_source.local.LocalSource.remoteDataSource
import co.farooq.paginationcompose.data.data_source.remote.Api
import co.farooq.paginationcompose.data.model.Data
import kotlinx.coroutines.delay
import java.lang.Exception

class Repository {

    suspend fun getItemsRemote(page: Int, pageSize: Int): Result<List<Data>> {
        delay(1000)
        return try {
            val result = Api.retrofit.getPassengers(page = page, size = pageSize)
            if (result.data.isEmpty()) {
                Result.success(emptyList())
            } else {
                Result.success(result.data)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getItemsLocal(page: Int, pageSize: Int): Result<List<ListItem>> {
        delay(2000L)
        val startingIndex = page * pageSize
        return if (startingIndex + pageSize <= remoteDataSource.size) {
            Result.success(remoteDataSource.subList(startingIndex, startingIndex + pageSize))
        } else Result.success(emptyList())
    }
}