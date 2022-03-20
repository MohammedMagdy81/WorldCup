package com.example.worldcub.data

import java.io.Serializable

data class Match(
 var year:Int ,
 val stadium:String ,
 val city:String ,
 val homeTeamName:String ,
 val awayTeamName:String ,
 val homeTeamGoals:Int ,
 val awayTeamGoals:Int
) :Serializable