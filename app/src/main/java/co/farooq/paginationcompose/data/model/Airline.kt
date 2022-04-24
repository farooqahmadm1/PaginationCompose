package co.farooq.paginationcompose.data.model


import com.google.gson.annotations.SerializedName

data class Airline(
    var country: String,
    var established: String,
    @SerializedName("head_quaters")
    var headQuaters: String,
    var id: Int,
    var logo: String,
    var name: String,
    var slogan: String,
    var website: String
)