package br.com.arllain.myweather.ui.main.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arllain.myweather.data.local.DataBaseApp
import br.com.arllain.myweather.data.local.model.Favorite
import br.com.arllain.myweather.databinding.FragmentFavoriteBinding
import br.com.arllain.myweather.databinding.FragmentSearchBinding
import br.com.arllain.myweather.extension.toPx
import br.com.arllain.myweather.ui.main.forecast.ForecastAdapter
import br.com.arllain.myweather.ui.main.search.SearchFragment
import br.com.arllain.myweather.util.MarginItemDecoration

class FavoriteFragment: Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val favoriteAdapter by lazy {
        FavoriteAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rvFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
            addItemDecoration(MarginItemDecoration(16.toPx()))
        }

        val dao = DataBaseApp.getInstance(requireContext()).getFavoriteDao()
        favoriteAdapter.submitList(dao.getAll())
    }
}