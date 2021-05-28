package com.example.projectcertification.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectcertification.databinding.ListItemBinding
import com.example.projectcertification.model.Words

class WordListAdapter(private val clickListener: (Words) -> Unit, private val dataSet: List<Words>) :
    RecyclerView.Adapter<WordListAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding_:  ListItemBinding) : RecyclerView.ViewHolder(binding_.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding  = ListItemBinding.inflate(layoutInflater,parent,false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.binding_.itemTitle.text = item.strName
        holder.binding_.itemTitle.setOnClickListener {
            clickListener(item)
        }
    }

    override fun getItemCount() = dataSet.size


}