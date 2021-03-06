package br.com.arllain.myweather.ui.main.search

import android.os.Bundle
import androidx.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arllain.myweather.R
import br.com.arllain.myweather.data.remote.RetrofitManager
import br.com.arllain.myweather.data.remote.model.FindResult
import br.com.arllain.myweather.databinding.FragmentSearchBinding
import br.com.arllain.myweather.extension.isInternetAvailable
import br.com.arllain.myweather.extension.toPx
import br.com.arllain.myweather.util.MarginItemDecoration
import br.com.arllain.myweather.util.ReadFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class SearchFragment: Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val searchAdapter by lazy {
        SearchAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun findCity(){
        val file = File(requireContext().filesDir, "owApiKey")
        val apiKey = ReadFile.readSafeFile(file, requireContext())
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        val units = sharedPreference.getString("key_temperature_unit", getString(R.string.unit_metric)).toString()
        val lang = sharedPreference.getString("key_language", getString(R.string.lang_english)).toString()
        var tempCF = getString(R.string.temp_celcius)
        if (units == getString(R.string.unit_imperial)) {
            tempCF = getString(R.string.temp_fahrenheit)
        }

        if (requireContext().isInternetAvailable()){
            val call = RetrofitManager.getOpenWeatherService()?.findCity(
                binding.edtSearch.text.toString(),
                units,
                lang,
                apiKey
            )

            call?.enqueue(object : Callback<FindResult> {
                override fun onResponse(call: Call<FindResult>, response: Response<FindResult>) {
                    if (response.isSuccessful) {
                        val cities = response.body()?.cities
                        cities?.forEach { city -> city.tempCF = tempCF }
                        searchAdapter.submitList(cities)
                    } else {
                        Log.w(TAG, "onResponse: ${response.message()}",)
                    }
                }

                override fun onFailure(call: Call<FindResult>, t: Throwable) {
                    Log.e(TAG, "onFailure", t)
                }

            })
        }else {
            Toast.makeText(requireContext(), "No network access", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initUi() {
        binding.rvCities.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = searchAdapter
            addItemDecoration(MarginItemDecoration(16.toPx()))
        }

        binding.btnSearch.setOnClickListener {
            findCity()
        }
    }

    companion object {
        private const val TAG = "SearchFragment"
    }
}