package com.psi.toserbalist.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.psi.toserbalist.R
import com.psi.toserbalist.adapter.BarangAdapter
import com.psi.toserbalist.models.ApiService
import com.psi.toserbalist.models.BarangLists
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Makanan.newInstance] factory method to
 * create an instance of this fragment.
 */
class Minuman : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic2VydmljZV9yb2xlIiwiaWF0IjoxNjMzNzAxNTI3LCJleHAiOjE5NDkyNzc1Mjd9.UWEACFqD20y4BX-vdAKYpAs5uwKRAuWK9jdQg9ktHBs"
        val apikey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoic2VydmljZV9yb2xlIiwiaWF0IjoxNjMzNzAxNTI3LCJleHAiOjE5NDkyNzc1Mjd9.UWEACFqD20y4BX-vdAKYpAs5uwKRAuWK9jdQg9ktHBs"

        var client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("apikey" , apikey)
                .build()
            chain.proceed(newRequest)
        }).build()

        val retrofit = Retrofit.Builder().client(client).baseUrl("https://wivzmfenumiglquwejaq.supabase.co/rest/v1/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val api = retrofit.create(ApiService::class.java)

        var view =  inflater.inflate(R.layout.fragment_makanan, container, false)
        api.fetchMakanan("*","eq.Minuman").enqueue(object : Callback<List<BarangLists>> {
            override fun onResponse(
                call: Call<List<BarangLists>>,
                response: Response<List<BarangLists>>

            ) {

                val recyclerView: RecyclerView = view.findViewById(R.id.recyvlerView)

                val layoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = BarangAdapter(response.body()!!)

                Log.d("fachry", "onResponse: ${response.body()!![0]}")

            }

            override fun onFailure(call: Call<List<BarangLists>>, t: Throwable) {
//                prepareItems()
            }

        })



        // menggunakan viewgroup linearlayout untuk menampulkan data
        // secara vertical

        return view
    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Makanan.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Makanan().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
}