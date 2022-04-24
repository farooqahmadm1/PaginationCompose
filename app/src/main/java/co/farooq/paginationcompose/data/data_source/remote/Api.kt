package co.farooq.paginationcompose.data.data_source.remote

import co.farooq.paginationcompose.data.model.Passengers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("v1/passenger")
    suspend fun getPassengers(@Query("page") page: Int, @Query("size") size: Int) : Passengers

    companion object {
        var retrofit: Api = Retrofit.Builder()
            .baseUrl("https://api.instantwebtools.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}