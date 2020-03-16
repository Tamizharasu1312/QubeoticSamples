package com.quebotic.qubeoticsamples.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.quebotic.qubeoticsamples.R
import com.quebotic.qubeoticsamples.pojo.DataRC
import com.quebotic.qubeoticsamples.utils.ItemMoveCallback2
import com.quebotic.qubeoticsamples.utils.StartDragListener
import java.util.*

class CustomAdapterDrag(val userList: ArrayList<DataRC>, val startDragListener: StartDragListener) : RecyclerView.Adapter<CustomAdapterDrag.ViewHolder>(), ItemMoveCallback2.ItemTouchHelperContractDrag {

    private val mArrayList = userList
   var mStartDragListener = startDragListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.custom_rcview_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            val aArrayListDetails = mArrayList[position]

            holder.textViewSNo!!.text = position.toString()
            holder.textViewName!!.text = aArrayListDetails.name
            holder.aCustom_li!!.setOnClickListener {
                //if(position!=0) {
                swapeItem(position, 0)
              //  }
            }
          /*  holder.aCustom_li!!.setOnTouchListener(OnTouchListener { v, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    mStartDragListener.requestDrag(holder)
                }
                false
            })*/
            holder.setIsRecyclable(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(var rowView: View) : RecyclerView.ViewHolder(rowView) {

        private var mTitle: TextView? = null
        var aCustom_li: LinearLayout? = null
        var textViewSNo: TextView? = null
        var textViewName: TextView? = null
        var textViewAddress: TextView? = null

        init {
            rowView = itemView
            aCustom_li = itemView.findViewById(R.id.custom_li)
            textViewSNo = itemView.findViewById(R.id.textViewSNo)
            textViewName = itemView.findViewById(R.id.textViewUsername)
            textViewAddress = itemView.findViewById(R.id.textViewAddress)
        }

    }

    fun swapeItem(fromPosition: Int, toPosition: Int) {
 /*       Collections.swap(mArrayList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        notifyDataSetChanged()*/

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mArrayList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mArrayList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
      //  notifyDataSetChanged()
       // notifyItemChanged(fromPosition)
        notifyItemRangeChanged(toPosition, mArrayList.size)

    }



    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mArrayList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mArrayList, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        notifyDataSetChanged()
    }


    override fun onRowSelected(myViewHolder: ViewHolder?) {
        myViewHolder!!.rowView!!.setBackgroundColor(Color.GRAY)
    }

    override fun onRowClear(myViewHolder: ViewHolder?) {
        myViewHolder!!.rowView!!.setBackgroundColor(Color.WHITE)
    }

}


