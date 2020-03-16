package com.quebotic.qubeoticsamples

import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    private var mContext: Context? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        mContext = this@MainActivity
        val aEditText: TextInputEditText = findViewById(R.id.form_add_EDT)
        val aEditTextInput: TextInputLayout = findViewById(R.id.form_add_TL)
        setDecimalPoint(aEditText)
       // aEditText.inputType = InputType.TYPE_CLASS_NUMBER + InputType.TYPE_NUMBER_FLAG_SIGNED + InputType.TYPE_NUMBER_FLAG_DECIMAL
     //   setDecimalPoint(aEditText)
        /*
                val aSwitchCompat: SwitchCompat = findViewById(R.id.switch_item)
                //aSwitchCompat.isEnabled = false
                aSwitchCompat.isChecked = true*/

        addDecimalValue("1", aEditText)
    }

    fun setFiledLength(aEditText: TextInputEditText, aLength: String?) {
        try {
            if (!aLength.equals("")) {
                val maxLength = aLength!!.toInt()
                aEditText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(maxLength))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDecimalPoint(aEditText: TextInputEditText) {

        try {


            aEditText.filters = arrayOf<InputFilter>(DecimalDigitsInputFilter("18".toInt(), "3".toInt()))
            setFiledLength(aEditText, "18")

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addDecimalValue(aLength: String, aEditText: TextInputEditText) {

        val amount  = java.lang.Double.parseDouble("1234.000")
        val aLengths=3
        var format = ""
        for (i in 0 until aLengths) {
            format += "0"
        }
       //val formatter: NumberFormat = DecimalFormat("#0.0000")
        val formatter: NumberFormat = DecimalFormat("#0."+format)

        aEditText.setText(formatter.format(amount))

    }

    fun generateNumberSigns(n: Int): String? {
        var s = ""
        for (i in 0 until n) {
            s += "#"
        }
        return s
    }
}
