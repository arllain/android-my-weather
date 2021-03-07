package br.com.arllain.myweather.ui.main.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.arllain.myweather.data.local.model.Favorite
import br.com.arllain.myweather.databinding.ItemCityFavoriteBinding

class FavoriteAdapter(
        private val onDeleteClick: (Favorite) -> Unit
): ListAdapter<Favorite,FavoriteAdapter.ViewHolder>(SearchDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCityFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteToBind = getItem(position)
        holder.bind(favoriteToBind)
    }

    inner class ViewHolder(private val binding: ItemCityFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
                binding.apply {
                    tvFavoriteCityName.text = favorite.city_name
                    tvFavoriteCountry.text = favorite.country_name
                    btnDelete.setOnClickListener {
                        onDeleteClick(favorite)
                    }
                }
        }
    }

    class SearchDiff: DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite) = oldItem.id == newItem.id
    }
}


