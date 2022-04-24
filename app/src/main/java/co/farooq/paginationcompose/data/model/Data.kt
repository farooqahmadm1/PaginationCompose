package co.farooq.paginationcompose.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    var airline: List<Airline>,
    @SerializedName("_id")
    var id: String,
    var name: String,
    var trips: Int,
    @SerializedName("__v")
    var v: Int
)