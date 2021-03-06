package com.quebotic.qubeoticsamples.slide.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.ers.tkenterprise.fragmentsmanager.QueboticFragmentManager
import com.quebotic.qubeoticsamples.R

class HomeFragment2 : Fragment(), View.OnClickListener {

    //Class Variables
    private lateinit var mContext: Context
    private lateinit var mView: View
    lateinit var mFragmentManager: QueboticFragmentManager

    //Layout Variables
    private lateinit var mIMHomeRight: ImageView
    private lateinit var mIMHomeLeft: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity!!
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.home_layout2, container, false)

        init()

        return mView
    }

    private fun init() {
        mFragmentManager = QueboticFragmentManager(activity!!)
        mIMHomeRight = mView.findViewById(R.id.im_home_right2)
        mIMHomeLeft = mView.findViewById(R.id.im_home_left2)
        mIMHomeRight.setOnClickListener(this)
        mIMHomeLeft.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.im_home_left2 -> {
                callFragment(HomeFragment())

            }
            R.id.im_home_right2 -> {
                // callFragment()
            }
        }
    }

    fun callFragment(aFragment: Fragment) {
        Log.e("aFragment", aFragment.tag.toString())
        mFragmentManager.updateContent(aFragment, aFragment.tag.toString(), null)
    }
}
