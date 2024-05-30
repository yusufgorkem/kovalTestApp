package com.example.kovaltestapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kovaltestapp.R
import com.example.kovaltestapp.model.Option

class RecyclerViewAdapter(
    private val context: Context,
    private val options: ArrayList<Option>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val head: TextView = view.findViewById(R.id.head)
        val body: TextView = view.findViewById(R.id.body)
        val arrowRight: ImageView = view.findViewById(R.id.arrowRight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.setImageResource(options[position].image)
        holder.head.text = context.getString(options[position].head)
        holder.body.text = context.getString(options[position].body)
        holder.arrowRight.setImageResource(R.drawable.arrow_right)
    }
}