package com.vasifgumbatov.networking.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.vasifgumbatov.networking.Api.ApiManager
import com.vasifgumbatov.networking.Api.ApiService
import com.vasifgumbatov.networking.Data.PostsResponse
import com.vasifgumbatov.networking.R
import com.vasifgumbatov.networking.RecyclerView.Adapter
import com.vasifgumbatov.networking.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FirstFragment : Fragment() {

    private var binding: FragmentFirstBinding? = null
    private val adapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        val client = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiCall = client.create(ApiService::class.java)
        apiCall.getPosts().enqueue(object : Callback<List<PostsResponse>> {
            override fun onResponse(
                call: Call<List<PostsResponse>>,
                response: Response<List<PostsResponse>>
            ) {
                adapter.addData(response.body().orEmpty())
            }

            override fun onFailure(p0: Call<List<PostsResponse>>, th: Throwable) {
//                Log.d("fail", "onFailure: ${th.localizedMessage}")
            }
        })

    }

}