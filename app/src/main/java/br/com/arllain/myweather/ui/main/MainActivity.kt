package br.com.arllain.myweather.ui.main

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.arllain.myweather.R
import br.com.arllain.myweather.databinding.ActivityMainBinding
import br.com.arllain.myweather.ui.main.favorite.FavoriteFragment
import br.com.arllain.myweather.ui.main.search.SearchFragment
import br.com.arllain.myweather.ui.main.settings.SettingsFragment
import br.com.arllain.myweather.util.CreateFile
import br.com.arllain.myweather.util.ReadFile
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val searchFragment   = SearchFragment()
    private val favoriteFragment = FavoriteFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verifyApiKey()
        initUi()
    }

    private fun initUi() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.menu_search -> setFragment(searchFragment)
                R.id.menu_favorite -> setFragment(favoriteFragment)
                R.id.menu_settings -> setFragment(settingsFragment)
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.menu_search
    }

    private fun verifyApiKey() {
        val file = File(filesDir, "owApiKey")
        val result = ReadFile.readSafeFile(file, applicationContext)

        if (result.isBlank()) {
            val builder = AlertDialog.Builder(this)
            val dialogApiKeyView = layoutInflater.inflate(R.layout.dialog_apikey, null)
            builder.apply {
                setView(dialogApiKeyView)
                setTitle(getString(R.string.warning))
                setMessage(getString(R.string.provideApiKey))
                setCancelable(false)

                setPositiveButton(resources.getString(R.string.ok)) { dialog, _ ->
                    val apiKey = dialogApiKeyView.findViewById<EditText>(R.id.apikey)
                    createFile(file, apiKey.text.toString())
                    dialog.dismiss()
                }
            }
            builder.create().show()
        }
    }

    private fun createFile(file: File, apiKey: String) {
        CreateFile.createSafeFile(file, apiKey, applicationContext)
    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(binding.containerView.id, fragment)
            .addToBackStack(null)
            .commit()
    }
}