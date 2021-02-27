package br.com.arllain.myweather.ui.main.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.arllain.myweather.data.local.DataBaseApp
import br.com.arllain.myweather.data.local.model.Favorite
import br.com.arllain.myweather.databinding.FragmentFavoriteBinding
import br.com.arllain.myweather.databinding.FragmentSearchBinding
import br.com.arllain.myweather.ui.main.search.SearchFragment

class FavoriteFragment: Fragment() {

    lateinit var binding: FragmentFavoriteBinding

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

        val dao = DataBaseApp.getInstance(requireContext()).getFavoriteDao()

        val favorite = Favorite(1, "Recife")
        dao.insert(favorite)

        dao.getAll().forEach {
            Log.d("DB", "onActivityCreated: $it")
        }
    }
}