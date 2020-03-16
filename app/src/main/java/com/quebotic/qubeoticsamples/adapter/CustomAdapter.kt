package com.quebotic.qubeoticsamples.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quebotic.qubeoticsamples.R
import com.quebotic.qubeoticsamples.pojo.DataRC
import com.quebotic.qubeoticsamples.pojo.User
import java.util.*

class CustomAdapter(val userList: ArrayList<DataRC>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val mArrayList = userList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_rcview_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val aArrayListDetails = mArrayList[position]

            holder.textViewSNo.text = position.toString()
            holder.textViewName.text = aArrayListDetails.name

            holder.aCustom_li.setOnClickListener {
                swapeItem(position,0)
            }

            holder.setIsRecyclable(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val aCustom_li: LinearLayout = itemView.findViewById(R.id.custom_li)
        val textViewSNo: TextView = itemView.findViewById(R.id.textViewSNo)
        val textViewName: TextView = itemView.findViewById(R.id.textViewUsername)
        val textViewAddress: TextView = itemView.findViewById(R.id.textViewAddress)

    }
    fun swapeItem(fromPosition: Int, toPosition: Int) {
        Collections.swap(mArrayList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyDataSetChanged()

    }

}


