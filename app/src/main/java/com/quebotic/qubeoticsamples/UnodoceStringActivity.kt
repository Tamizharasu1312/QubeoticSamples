package com.quebotic.qubeoticsamples

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.unicodestring_layout.*
import me.abhinay.input.CurrencyEditText
import java.util.regex.Pattern


class UnodoceStringActivity : AppCompatActivity() {

    private var mContext: Context? = null

    //Layout Controls
    var mRG_gender: RadioGroup? = null
    var mRB_Male: RadioButton? = null
    var mRB_Female: RadioButton? = null
    var aEditText: TextInputEditText? = null
    var aEditTextInput: TextInputLayout? = null
    var aEditText2: TextInputEditText? = null
    var aEditTextInput2: TextInputLayout? = null
    var mUnocodeString_TV: TextView? = null
    var mClick_BT: TextView? = null
    var etInput: CurrencyEditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.unicodestring_layout)

        mContext = this@UnodoceStringActivity

        mRG_gender = findViewById(R.id.rg_gender)
        mClick_BT = findViewById(R.id.click_BT)
        mUnocodeString_TV = findViewById(R.id.UnocodeString_TV)
        mUnocodeString_TV!!.text = getString(R.string.euro_symbol)


        aEditText = findViewById(R.id.form_add_EDT)
        aEditTextInput = findViewById(R.id.form_add_TL)

        aEditText2 = findViewById(R.id.form_add_EDT2)
        aEditTextInput2 = findViewById(R.id.form_add_TL2)


        etInput = findViewById(R.id.etInput)
        //  etInput!!.setCurrency(getString(R.string.dollor_symbol))
        etInput!!.setCurrency("$")
        etInput!!.setDelimiter(false)
        etInput!!.setSpacing(false)
        etInput!!.setDecimals(false)
        //Make sure that Decimals is set as false if a custom Separator is used
        //etInput!!.setSeparator(".")

        aEditText!!.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                aEditText!!.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_dollar_symbol, 0, 0, 0)
            } else {
                aEditText!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
        }
        clickListener()
        edittextListener()

        val aSwitchCompat: SwitchCompat = findViewById(R.id.form_inflate_switch)
        val aSwitchCompatLayout: LinearLayout = findViewById(R.id.form_inflate_SwitchCompat_layout)
    //   aSwitchCompat.isEnabled=false
       aSwitchCompat.isChecked=true


    }


    private fun clickListener() {

        mClick_BT!!.setOnClickListener {
            /*  val aValue = aEditText2!!.text.toString().trim()
              if (!aValue.isEmpty()) {
                  Toast.makeText(mContext!!, aValue, Toast.LENGTH_SHORT).show()
              }*/
            val id: Int = rg_gender.checkedRadioButtonId
            if (id != -1) { // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio: RadioButton = findViewById(id)
                Toast.makeText(applicationContext, "On button click :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
            } else {
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext, "On button click :" + " nothing selected", Toast.LENGTH_SHORT).show()
            }
        }
        mRG_gender!!.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            // Toast.makeText(applicationContext, " On checked change :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
            Toast.makeText(applicationContext, " On checked change :" + " ${radio.text}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun edittextListener() {


        val CURRENCT_PATTERN: Pattern = Pattern.compile("^\\$(\\d{1,3}(,\\d{3})*|(\\d+))(\\.\\d{2})?$")

        aEditText2!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {

                // if (!s.toString().matches("^\\$(\\d{1,3}(\\,\\d{3})*|(\\d+))(\\.\\d{2})?$"))
                if (!CURRENCT_PATTERN.matcher(s.toString()).matches()) {
                    val userInput = "" + s.toString().replace("[^\\d]", "")
                    val cashAmountBuilder = StringBuilder(userInput)
                    while (cashAmountBuilder.length > 3 && cashAmountBuilder.get(0) == '0') {
                        cashAmountBuilder.deleteCharAt(0)
                    }
                    while (cashAmountBuilder.length < 3) {
                        cashAmountBuilder.insert(0, '0')
                    }
                    cashAmountBuilder.insert(cashAmountBuilder.length - 2, '.')
                    aEditText2!!.removeTextChangedListener(this)
                    aEditText2!!.setText(cashAmountBuilder.toString())
                    aEditText2!!.setTextKeepState("$" + cashAmountBuilder.toString())
                    Selection.setSelection(aEditText2!!.getText(), cashAmountBuilder.toString().length + 1)
                    aEditText2!!.addTextChangedListener(this)
                }
            }
        })

    }

}
