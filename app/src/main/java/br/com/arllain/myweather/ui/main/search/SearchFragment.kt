package br.com.arllain.myweather.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.arllain.myweather.databinding.FragmentSearchBinding
import br.com.arllain.myweather.extension.isInternetAvailable


class SearchFragment: Fragment() {

    lateinit var binding: FragmentSearchBinding

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
        binding.btnSearch.setOnClickListener {
            if (requireContext().isInternetAvailable()){
                Toast.makeText(requireContext(), "Has internet", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(requireContext(), "Has no internet", Toast.LENGTH_SHORT).show()
            }
        }
    }



}