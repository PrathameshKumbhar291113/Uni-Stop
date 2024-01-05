package com.unistop.show_university_list_feature.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unistop.R

class UniversityListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university_list)

        setupUi()
    }

    private fun setupUi() {
        window.statusBarColor = getColor(R.color.red)
    }
}