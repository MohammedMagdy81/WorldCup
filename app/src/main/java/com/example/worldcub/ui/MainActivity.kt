package com.example.worldcub.ui

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import com.example.worldcub.MatchAdapter
import com.example.worldcub.data.DataManger
import com.example.worldcub.data.Match

import com.example.worldcub.databinding.ActivityMainBinding
import com.example.worldcub.util.Constants
import com.example.worldcub.util.CsvParser
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import com.example.worldcub.data.DataManger.addMatch as addMatch1

class MainActivity : BaseActivity<ActivityMainBinding>() ,MatchAdapter.MatchInteractionListener{

    override val LOG_TAG: String = "ACTIVITY_MAIN"
    override val bindingInflater: (layoutInflater: LayoutInflater) -> ActivityMainBinding
       =ActivityMainBinding::inflate

    lateinit var adapter: MatchAdapter

    override fun setup() {
        openFile()
        adapter= MatchAdapter(DataManger.matches,this)
        binding?.recyclerView?.adapter= adapter
    }

    override fun addCallBacks() {
        binding?.fab?.setOnClickListener {
            addFinalMatch()
        }

    }

    private fun addFinalMatch() {
        val finalMatch= Match(year = 2018,"Mosco","Russia","Franca"
            ,"Croatia",4,2)
        DataManger.addMatchAtIndex(finalMatch,2)
        adapter.setData(DataManger.matches)
    }

    private fun openFile() {
        val inputStream: InputStream = assets.open("worldcup.csv")
        val buffer= BufferedReader(InputStreamReader(inputStream))
        buffer.forEachLine { line->
            val currentMatch = CsvParser.parse(line)
            addMatch1(currentMatch)
        }

    }

    override fun OnTeamClickListener(name: String) {
        Toast.makeText(this,name,Toast.LENGTH_LONG).show()
    }

    override fun OnItemClickListener(match: Match) {
        val intent= Intent(this, DetailesActivity::class.java)
        intent.putExtra(Constants.MATCH_KEY,match)
        startActivity(intent)
    }

    override fun onDeleteClickListener(index: Int) {
        DataManger.deleteMatchAtIndex(index)
        adapter.setData(DataManger.matches)
    }


}