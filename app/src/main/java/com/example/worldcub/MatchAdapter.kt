package com.example.worldcub


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.worldcub.data.Match
import com.example.worldcub.databinding.ItemMatchBinding
import com.example.worldcub.databinding.ItemMatchHeaderBinding
import com.example.worldcub.util.MatchDiffUtil

class MatchAdapter(private var list:List<Match>, private var listener:MatchInteractionListener) :RecyclerView.Adapter<MatchAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        when(viewType){
            VIEW_TYPE_HEADER->{
                val v= LayoutInflater.from(parent.context).inflate(R.layout.item_match_header,parent,false)
                return HeaderViewHolder(v)
            }
            VIEW_TYPE_MATCH->{
                val v= LayoutInflater.from(parent.context).inflate(R.layout.item_match,parent,false)
                return MatchViewHolder(v)
            }

        }
        return super.createViewHolder(parent,viewType)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentMatch= list[position]
        when(holder){
            is MatchViewHolder->{
                holder.apply {
                    binding.apply {
                        textTeam1.text=currentMatch.homeTeamName
                        textTeam2.text=currentMatch.awayTeamName
                        textTeam1Goals.text=currentMatch.homeTeamGoals.toString()
                        textTeam2Goals.text=currentMatch.awayTeamGoals.toString()
                        matchYear.text=currentMatch.year.toString()
                        stadium.text = currentMatch.stadium
                        textTeam1.setOnClickListener {
                            listener.OnTeamClickListener(currentMatch.homeTeamName)
                        }
                        textTeam2.setOnClickListener {
                            listener.OnTeamClickListener(currentMatch.awayTeamName)
                        }
                        root.setOnClickListener {
                            listener.OnItemClickListener(currentMatch)
                        }
                        icDelete.setOnClickListener {
                            listener.onDeleteClickListener(position)
                        }

                    }

                }
            }
            is HeaderViewHolder->{
                if (position==0){
                    holder.bindingHeader.textYear.text=(currentMatch.year).toString()
                }
                else{
                    holder.bindingHeader.textYear.text=(currentMatch.year+4).toString()
                }

            }
        }

    }

    fun setData(newList:List<Match>){
        val diffResult= DiffUtil.calculateDiff(MatchDiffUtil(list,newList))
        list= newList
        diffResult.dispatchUpdatesTo(this)
        //notifyDataSetChanged()
    }

    interface MatchInteractionListener{
        fun OnTeamClickListener(name:String)
        fun OnItemClickListener(natch:Match)
        fun onDeleteClickListener(index:Int)
    }

    abstract class BaseViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    class MatchViewHolder(itemView: View):BaseViewHolder(itemView) {
        val binding = ItemMatchBinding.bind(itemView)
    }
     class HeaderViewHolder(itemView: View):BaseViewHolder(itemView){
         val bindingHeader= ItemMatchHeaderBinding.bind(itemView)
     }


    override fun getItemCount()= list.size

    override fun getItemViewType(position: Int): Int {
        val positionplus= position+1
        return if (position==0||list[position].year!=list[positionplus].year){
            VIEW_TYPE_HEADER
        }else{
            VIEW_TYPE_MATCH
        }
    }
    companion object{
        const val VIEW_TYPE_HEADER= 11
        const val VIEW_TYPE_MATCH= 12
    }

}







