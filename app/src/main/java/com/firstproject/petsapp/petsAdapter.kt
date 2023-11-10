package com.firstproject.petsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class petsAdapter(private val petList: ArrayList<Pet>) : RecyclerView.Adapter<petsAdapter
.MyViewHolder>() {
    
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.pets_list,
            parent, false
        )
        return MyViewHolder(itemView)
        
    }
    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        
        val currentitem = petList[position]
        
        holder.petName.text = currentitem.name
        holder.petSpecie.text = currentitem.specie
        Picasso.get().load(currentitem.image).into(holder.petImage)
        
        /* holder.itemView.setOnClickListener({
             itemClickListener.onItemClick()
         })*/
        
    }
    
    override fun getItemCount(): Int {
        
        return petList.size
    }
    
    
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        
        val petName: TextView = itemView.findViewById(R.id.petName)
        val petSpecie: TextView = itemView.findViewById(R.id.species)
        val petImage: ImageView = itemView.findViewById(R.id.image)
        
    }
    
}