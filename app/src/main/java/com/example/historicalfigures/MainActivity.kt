package com.example.historicalfigures

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.historicalfigures.R
import com.example.historicalfigures.databinding.ActivityMainBinding
import com.example.historicalfigures.fragment.FigureFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val figureFragment = FigureFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentcont, figureFragment)
            .commit()

    }
}