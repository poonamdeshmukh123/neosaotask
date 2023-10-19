package com.example.neosaotask.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.neosaotask.R
import com.example.neosaotask.api.api.model.EntryModel
import com.example.neosaotask.api.api.model.UniverSityResponse

class UniversityAdapter(private val data: List<EntryModel>) : RecyclerView.Adapter<Universityviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Universityviewholder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item, parent, false)
        return Universityviewholder(itemView)
    }

    override fun onBindViewHolder(holder: Universityviewholder, position: Int) {
        val item = data[position]
        holder.linktxt.text="link= "+item.link
        holder.catagorytxt.text="category="+item.category
        // Bind other data to your views
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class Universityviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val linktxt=itemView.findViewById<TextView>(R.id.linktxt)
    val catagorytxt=itemView.findViewById<TextView>(R.id.catagorytxt)

}
