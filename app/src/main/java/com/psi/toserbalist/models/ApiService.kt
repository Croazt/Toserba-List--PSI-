package com.psi.toserbalist.models

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
     @GET("Barang")

    fun fetchMakanan(@Query("select") select : String,@Query("Category",) kategori : String): Call<List<BarangLists>>
}