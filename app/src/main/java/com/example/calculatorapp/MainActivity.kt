package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lastOp = '~'
        var lastVal = "0"

        btnEquals.setOnClickListener{
            val result = getResult(tvResult.text.toString().trim(), lastVal, lastOp)
            tvResult.text = result
        }
        btn0.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '0')
        }
        btn1.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '1')
        }
        btn2.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '2')
        }
        btn3.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '3')
        }
        btn4.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '4')
        }
        btn5.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '5')
        }
        btn6.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '6')
        }
        btn7.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '7')
        }
        btn8.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '8')
        }
        btn9.setOnClickListener{
            tvResult.text = numberInput(tvResult.text.toString(), '9')
        }
        btnDecimal.setOnClickListener{
            val currInput = tvResult.text.toString().trim()
            if (!currInput.contains('.')) {
                tvResult.text = String.format("%s%c", currInput, '.')
            }
        }
        btnC.setOnClickListener{
            tvResult.text = "0"
            lastOp = '~'
            lastVal = "0"
            tvHistory.text = ""
        }

        btnCE.setOnClickListener{
            tvResult.text = "0"
        }

        btnPlus.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '+'
            tvResult.text = "0"
        }
        btnMinus.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '-'
            tvResult.text = "0"
        }
        btnDiv.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '/'
            tvResult.text = "0"
        }
        btnMul.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '*'
            tvResult.text = "0"

        }
        btnNeg.setOnClickListener {
            if (tvResult.text.startsWith('-')) {
                tvResult.text = tvResult.text.toString().drop(1)
            } else {
                tvResult.text = String.format("%c%s", '-', tvResult.text.toString())
            }
        }
    }

    private fun addToHistory(oldHist: String, value: String, op: String) {

    }

    private fun getResult(toCompute: String, lastVal: String, lastOp: Char) : String {
        var retVal ="ERROR"
        if (lastOp == '~') { return toCompute }
        when(lastOp) {
            '+' -> retVal = (lastVal.toDouble() + toCompute.toDouble()).toString()
            '-' -> retVal = (lastVal.toDouble() - toCompute.toDouble()).toString()
            '*' -> retVal = (lastVal.toDouble() * toCompute.toDouble()).toString()
            '/' -> retVal = (lastVal.toDouble() / toCompute.toDouble()).toString()
        }
        retVal = retVal.removeSuffix(".0")
        return retVal
    }

    private fun numberInput(oldNum: String, numToAdd: Char): String {
        if (oldNum == "0") { return numToAdd.toString() }
        return String.format("%s%s", oldNum, numToAdd)
    }


}
