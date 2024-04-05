package com.example.historicalfigures.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.historicalfigures.R
import com.example.historicalfigures.adapter.FigureAdapter
import com.example.historicalfigures.databinding.FragmentHistoryFiguresBinding
import com.example.historicalfigures.model.FigureApi
import com.example.historicalfigures.model.FigureApiResponse
import com.example.historicalfigures.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FigureFragment : Fragment() {

    private var _binding: FragmentHistoryFiguresBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = FigureFragment()
    }

    private var adapter: FigureAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryFiguresBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = FigureAdapter { FigureApiResponse ->
            Toast.makeText(context, FigureApiResponse.name, Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.adapter = adapter

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { fetchFigures(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun fetchFigures(query: String) {
        ApiClient.instance.fetchPersonList(query).enqueue(object : Callback<List<FigureApiResponse>> {
            override fun onResponse(
                call: Call<List<FigureApiResponse>>,
                response: Response<List<FigureApiResponse>>
            ) {
                if (response.isSuccessful) {
                    Log.d("PersonListFragment", "Success: ${response.body()}")
                    adapter?.submitList(response.body())
                } else {
                    Log.e("PersonListFragment", "Error: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FigureApiResponse>>, t: Throwable) {
                Toast.makeText(context, "No connection: ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }




}



