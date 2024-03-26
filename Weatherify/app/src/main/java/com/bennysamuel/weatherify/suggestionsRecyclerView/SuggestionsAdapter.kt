package com.bennysamuel.weatherify.suggestionsRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bennysamuel.weatherify.R

class SuggestionsAdapter (private var suggestionsList: ArrayList<SuggestionsRecyclerData>): RecyclerView.Adapter<SuggestionsAdapter.SuggestionsViewHolder>() {

    var onItemClick: ((SuggestionsRecyclerData) -> Unit)? = null
    class SuggestionsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.suggestText)
        val card: LinearLayout = itemView.findViewById(R.id.suggest_card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suggestions_card, parent, false)
        return SuggestionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suggestionsList.size
    }

    override fun onBindViewHolder(holder: SuggestionsViewHolder, position: Int) {
        val suggestion = suggestionsList[position]
        println("suggesting"+ suggestion.searchTerm)
        holder.textView.text = suggestion.suggestionsText
        holder.card.setOnClickListener {
            onItemClick?.invoke(suggestion)
        }
    }
    fun updateData(newData: ArrayList<SuggestionsRecyclerData>) {
        suggestionsList = newData
        notifyDataSetChanged()
    }
}