package com.example.worldcub.ui

import android.view.LayoutInflater
import com.example.worldcub.data.Match
import com.example.worldcub.databinding.ActivityDetailesBinding
import com.example.worldcub.util.Constants

class DetailesActivity : BaseActivity<ActivityDetailesBinding>() {
    override val LOG_TAG: String
        get() = "ACTIVITY_DETAILS"
    override val bindingInflater: (layoutInflater: LayoutInflater) -> ActivityDetailesBinding
        get() = ActivityDetailesBinding::inflate

    override fun setup() {
        val match : Match?= intent.getSerializableExtra(Constants.MATCH_KEY) as Match?
        match.let {match->
            binding?.textTeam1?.text=match?.homeTeamName
            binding?.textTeam2?.text=match?.awayTeamName
            binding?.textTeam1Goals?.text=match?.homeTeamGoals.toString()
            binding?.textTeam2Goals?.text=match?.awayTeamGoals.toString()
            binding?.stadium?.text= match?.stadium
            binding?.matchYear?.text= match?.year.toString()
        }
    }

    override fun addCallBacks() {
    }

}