package com.quebotic.qubeoticsamples.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.quebotic.qubeoticsamples.R
import com.quebotic.qubeoticsamples.adapter.CustomAdapterDrag
import com.quebotic.qubeoticsamples.pojo.DataRC
import com.quebotic.qubeoticsamples.utils.ItemMoveCallback2
import com.quebotic.qubeoticsamples.utils.StartDragListener

class RecyclerviewActivity : AppCompatActivity(), StartDragListener {

    private var mContext: Context? = null

    var touchHelper: ItemTouchHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview_layout)

        mContext = this@RecyclerviewActivity


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(mContext!!, RecyclerView.VERTICAL, false)


        val aDataRC = ArrayList<DataRC>()

        aDataRC.add(DataRC("Bike"))
        aDataRC.add(DataRC("Car"))
        aDataRC.add(DataRC("Bus"))
        aDataRC.add(DataRC("Train"))

        //creating our adapter
        val adapter = CustomAdapterDrag(aDataRC, this)

        val callback: ItemTouchHelper.Callback = ItemMoveCallback2(adapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper!!.attachToRecyclerView(recyclerView)
        recyclerView.adapter = adapter

    }

    override fun requestDrag(viewHolder: RecyclerView.ViewHolder?) {
        touchHelper!!.startDrag(viewHolder!!)
    }
}