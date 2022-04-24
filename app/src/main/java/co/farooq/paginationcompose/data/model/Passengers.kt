package co.farooq.paginationcompose.data.model


data class Passengers(
    var `data`: List<Data>,
    var totalPages: Int,
    var totalPassengers: Int
)