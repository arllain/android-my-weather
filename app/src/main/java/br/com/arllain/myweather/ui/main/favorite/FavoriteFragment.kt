package br.com.arllain.myweather.ui.main.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.arllain.myweather.data.local.DataBaseApp
import br.com.arllain.myweather.data.local.FavoriteDao
import br.com.arllain.myweather.databinding.FragmentFavoriteBinding
import br.com.arllain.myweather.extension.toPx
import br.com.arllain.myweather.util.MarginItemDecoration

class FavoriteFragment: Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var dao: FavoriteDao

    private val favoriteAdapter by lazy {
        FavoriteAdapter{ favorite ->
            dao.delete(favorite)
            updateFavoriteList()
        }
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

        dao = DataBaseApp.getInstance(requireContext()).getFavoriteDao()
        favoriteAdapter.submitList(dao.getAll())

        binding.btnSearch.setOnClickListener {
            if (binding.edtSearch.text.isNotBlank()) {
                favoriteAdapter.submitList(dao.getByCidadeOrCountry("%" + binding.edtSearch.text.toString() + "%"))
            }else{
                updateFavoriteList()
            }
        }

    }

    private fun updateFavoriteList(){
        favoriteAdapter.submitList(dao.getAll())
    }

}