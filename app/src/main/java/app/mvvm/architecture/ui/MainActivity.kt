package app.mvvm.architecture.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import app.mvvm.architecture.R
import app.mvvm.architecture.databinding.ActivityMainBinding
import app.mvvm.architecture.util.extensions.ActivityExtensions.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupWithNavController(
            binding.toolbar,
            findNavController(R.id.mainNavHostFragment)
        )
    }
}