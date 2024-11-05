package com.example.databaseapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.databaseapp.data.Person

class PersonRvAdapter(private val list: List<Person>): RecyclerView.Adapter<PersonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_person, parent, false)
        return PersonViewHolder(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.idTv.text = list[position].id.toString()
        holder.nameTv.text = "Name: " + list[position].name.toString()
        holder.ageTv.text = "Age: "+ list[position].age.toString()
        holder.jobTv.text = "Job: "+ list[position].job.toString()
        holder.itemView.setOnClickListener(){
            val intent = Intent(holder.itemView.context, ProfileActivity::class.java)
            intent.putExtra("id", list[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }
}

class PersonViewHolder(item: View): RecyclerView.ViewHolder(item){
    val idTv = item.findViewById<TextView>(R.id.id_tv)
    val nameTv = item.findViewById<TextView>(R.id.name_tv)
    val ageTv = item.findViewById<TextView>(R.id.age_tv)
    val jobTv = item.findViewById<TextView>(R.id.job_tv)
}