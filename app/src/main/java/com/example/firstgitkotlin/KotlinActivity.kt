package com.example.firstgitkotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat

class KotlinActivity : AppCompatActivity() {


    private var eNumber: TextInputLayout? = null
    private var limit: TextView? = null
    private var okladTV: TextView? = null
    private var opvTV: TextView? = null
    private var ipnTV: TextView? = null
    private var oklad12TV: TextView? = null
    private var salarynarukuTV: TextView? = null
    private var salary12TV: TextView? = null

    companion object {
        private const val MZP:Long = 42500
        private const val OKLADN: Double = 0.1
        private const val TIMESSALARY: Int = 12
        private const val DIVNUMBER: Double = 0.81
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        okladTV  = findViewById(R.id.oklad)
        opvTV = findViewById(R.id.opv)
        ipnTV = findViewById(R.id.ipn)
        oklad12TV = findViewById(R.id.oklad12)
        salarynarukuTV = findViewById(R.id.salarynaruku)
        salary12TV = findViewById(R.id.salary12)

        eNumber = findViewById(R.id.edittext)
        limit = findViewById(R.id.limit)

        eNumber?.editText?.doOnTextChanged { text, _, _, _ ->
            if (!text.isNullOrBlank()) {

                val salary = text.toString().toLong()

                if (salary > 1_000_000_000) {
                    limit?.text = getString(R.string.limit)
                    limit?.visibility = View.VISIBLE
                } else {
                    limit?.visibility = View.GONE


                var oklad = ((salary.minus(OKLADN * MZP)).div(DIVNUMBER)).toLong()
                var okladY = (oklad.times(TIMESSALARY))
                var opv = (oklad.times(OKLADN)).toLong()
                var ipn = if (oklad == MZP) {
                    0
                } else (oklad.minus(opv).minus(MZP)).times(OKLADN).toLong()

                val salarynaruku = oklad.minus(opv).minus(ipn)
                val salaryY = salarynaruku.times(TIMESSALARY)

                if (oklad < 0 || ipn < 0 || opv < 0 || okladY < 0) {
                    oklad = 0
                    ipn = 0
                    opv = 0
                    okladY = 0
                }

                okladTV?.text = getString(R.string.tenge, formatting(oklad))

                opvTV?.text = getString(R.string.tenge, formatting(opv))

                ipnTV?.text = getString(R.string.tenge, formatting(ipn))

                oklad12TV?.text = getString(R.string.tenge, formatting(okladY))

                salarynarukuTV?.text = getString(R.string.tenge, formatting(salarynaruku))

                salary12TV?.text = getString(R.string.tenge, formatting(salaryY))
                }
        }
            else{
                limit?.text = getString(R.string.valid_number)
                limit?.visibility = View.VISIBLE

            }
        }
    }

}


private fun formatting(value: Long): String {
    return DecimalFormat("#,###").format(value)

}