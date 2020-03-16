package com.quebotic.qubeoticsamples

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class DragDropActivity : AppCompatActivity() {

    private var mContext: Context? = null

    // Array images
    private val images = intArrayOf(R.drawable.android, R.drawable.batman, R.drawable.deadpool, R.drawable.gambit, R.drawable.hulk, R.drawable.mario, R.drawable.wolverine, R.drawable.daredevil)

    // Array names
    // Description text
    private var textDescription: String? = "Subtitle description,lorem ipsum text generic etc"
    private val names = arrayOf("Android", "Batman", "DeadPool", "Gambit", "Hulk", "Mario", "Wolverine", "Daredevil")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drag_drop)

        mContext = this@DragDropActivity
        val mRecyclerView:RecyclerView = findViewById(R.id.recyclerView)


    }


}
