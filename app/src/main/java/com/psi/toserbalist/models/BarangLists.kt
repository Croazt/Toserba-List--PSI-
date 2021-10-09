package com.psi.toserbalist.models

import retrofit2.http.GET

data class BarangLists (
    val Title : String,
    val Category : String,
    val Deskripsi : String,
    val Gambar : String
)