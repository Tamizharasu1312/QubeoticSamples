package com.quebotic.qubeoticsamples.slide.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ers.tkenterprise.fragmentsmanager.QueboticFragmentManager
import com.quebotic.qubeoticsamples.R
import com.quebotic.qubeoticsamples.slide.screens.HomeFragment

class SlideActivity : AppCompatActivity() {

    //Class Variables
    private var mContext: Context? = null

    lateinit var mFragmentManager: QueboticFragmentManager
    //Layout Variables


    //Variables


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.slider_layout)

        mContext = this@SlideActivity

        init()
    }

    fun init() {
        mFragmentManager = QueboticFragmentManager(this)

        callFragment()
    }

    fun callFragment() {
        mFragmentManager.updateContent(HomeFragment(), "HomeFragment", null)
    }

}