package com.example.worldcub.data

object DataManger {
    private val matchesList= mutableListOf<Match>()
     val matches: List<Match> get() = matchesList.toList()
    private var matchIndex= 0

    fun addMatchAtIndex(match:Match,index:Int){
        matchesList.add(index,match)
    }

    fun addMatch(match: Match){
        matchesList.add(match)
    }
    fun getCurrentMatch(): Match= matchesList[matchIndex]
    fun getNextMatch():Match {
        matchIndex++
        if (matchIndex== matchesList.size){
            matchIndex=0
        }
        return matchesList[matchIndex]
    }
    fun getPreviosMatch():Match {
        matchIndex--
        if (matchIndex==-1){
            matchIndex= matchesList.size-1
        }
        return matchesList[matchIndex]
    }
    fun deleteMatchAtIndex(index: Int){
        matchesList.removeAt(index)
    }
}