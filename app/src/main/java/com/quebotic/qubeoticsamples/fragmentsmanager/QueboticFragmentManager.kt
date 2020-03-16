package com.ers.tkenterprise.fragmentsmanager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.quebotic.qubeoticsamples.R

class QueboticFragmentManager(private val myContext: FragmentActivity) {

    val mBackStackCount: Int
        get() {
            val aFragmentManager = myContext.supportFragmentManager
            return aFragmentManager.backStackEntryCount
        }

    /**
     * Update the Current Fragment by passing the below parameters
     *
     * @param aFragment Fragment
     * @param tag       String
     * @param aBundle   Bundle
     */
    fun updateContent(aFragment: Fragment, tag: String, aBundle: Bundle?) {
        try {

            Log.e("TAG Screen name", tag)
            // Initialise Fragment Manager
            val aFragmentManager = myContext.supportFragmentManager

            // Initialise Fragment Transaction
            val aTransaction = aFragmentManager.beginTransaction()

            if (aBundle != null) {
                aFragment.arguments = aBundle
            }
         // aTransaction.setCustomAnimations(R.anim.sliding_in_left, R.anim.sliding_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
           // aTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left)
            aTransaction.setCustomAnimations(R.anim.exit_from_left, R.anim.enter_from_right)
            // Add the selected fragment
            aTransaction.replace(R.id.main_container, aFragment, tag)

            // add the tag to the backStack
           // aTransaction.addToBackStack(tag)

            // Commit the Fragment transaction
            // aTransaction.commit ();

            aTransaction.commitAllowingStateLoss()

            myLastTag = tag
            Log.i("LastTag", myLastTag)

        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Clear All Fragments
     */
    fun clearAllFragments() {
        try {
            val aFragmentManager = myContext.supportFragmentManager

            for (i in 0 until aFragmentManager.backStackEntryCount) {
                aFragmentManager.popBackStack()
            }
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun oneStepBack() {
        val fts = myContext.supportFragmentManager.beginTransaction()
        val fragmentManager = myContext.supportFragmentManager
        if (fragmentManager.backStackEntryCount >= 2) {
            fragmentManager.popBackStackImmediate()
            fts.commit()
        }
    }

    fun onBackPress() {
        try {
            val aFragmentManager = myContext.supportFragmentManager
            if (aFragmentManager.backStackEntryCount > 1) {
                aFragmentManager.popBackStack()
                aFragmentManager.executePendingTransactions()
                Log.d("TAG", "CURRENT FRAGMENT BACK STACK CLASS " + aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name!!)

                //TODO Stop video
                val aFragmentTag = aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name
                val aFragment = myContext.supportFragmentManager.findFragmentByTag(aFragmentTag)
                aFragment!!.onResume()
                aFragmentManager.getBackStackEntryAt(aFragmentManager.backStackEntryCount - 1).name
            }
        } catch (e: StackOverflowError) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //Get the Current TAG
    fun getActiveFragmentTAG(): String? {
        return if (myContext.supportFragmentManager.backStackEntryCount == 0) {
            null
        } else myContext.supportFragmentManager.getBackStackEntryAt(myContext.supportFragmentManager.backStackEntryCount - 1).name
    }

    //Get the Current Fragment
    fun getActiveFragment(): Fragment? {
        return if (myContext.supportFragmentManager.backStackEntryCount == 0) {
            null
        } else {
            myContext.supportFragmentManager.findFragmentByTag(myContext.supportFragmentManager.getBackStackEntryAt(myContext.supportFragmentManager.backStackEntryCount - 1).name)!!
        }
    }

    companion object {
        /**
         * Last fragment tag
         */
        private var myLastTag = ""
    }

    // Remove fragment
    fun removeFragment(aCount: Int) {

        val aFragmentManager = myContext.supportFragmentManager
        for (i in 0 until aCount) {
            aFragmentManager.popBackStack()
        }
    }
}