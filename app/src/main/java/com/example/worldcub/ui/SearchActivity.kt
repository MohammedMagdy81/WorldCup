package com.example.worldcub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.worldcub.R
import com.example.worldcub.databinding.ActivityMainBinding
import com.example.worldcub.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override val LOG_TAG: String = "SEARCH_ACTIVITY"

    override val bindingInflater: (layoutInflater: LayoutInflater) -> ActivitySearchBinding
       = ActivitySearchBinding::inflate
    override fun setup() {

    }

    override fun addCallBacks() {
        TODO("Not yet implemented")
    }

}