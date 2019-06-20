package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    private var clearRes = false
    private var clearHist = false
    private var firstUse = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lastOp = '~'
        var lastVal = "0"

        btnEquals.setOnClickListener{
            addToHistory(tvResult.text.toString())
            val result = getResult(tvResult.text.toString().trim(), lastVal, lastOp)
            tvResult.text = result
            clearRes = true
            clearHist = true
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
            addToHistory(lastVal, lastOp)
        }
        btnMinus.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '-'
            tvResult.text = "0"
            addToHistory(lastVal, lastOp)
        }
        btnDiv.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '/'
            tvResult.text = "0"
            addToHistory(lastVal, lastOp)
        }
        btnMul.setOnClickListener{
            lastVal = tvResult.text.toString().trim()
            lastOp = '*'
            tvResult.text = "0"
            addToHistory(lastVal, lastOp)
        }
        btnNeg.setOnClickListener {
            if (tvResult.text.startsWith('-')) {
                tvResult.text = tvResult.text.toString().drop(1)
            } else {
                tvResult.text = String.format("%c%s", '-', tvResult.text.toString())
            }
        }

        btnDel.setOnClickListener{
            if (tvResult.text.isNotEmpty()) {
                tvResult.text = tvResult.text.toString().dropLast(1)
            }
        }
    }

    private fun addToHistory(value: String, op: Char = '~') {
        var oldHist = tvHistory.text.toString().trim()
        if (clearHist) {
            oldHist = ""
            clearHist = false
        }
        if (op == '~') {
            tvHistory.text = String.format("%s%s", oldHist, value)
        } else {
            tvHistory.text = String.format("%s%s%s", oldHist, value, op)
        }
    }

    private fun getResult(toCompute: String, lastVal: String, lastOp: Char) : String {
        var retVal ="ERROR"
        if (lastOp == '~') { return toCompute }
        when(lastOp) {
            '+' -> retVal = (lastVal.toDouble().plus(toCompute.toDouble())).toString()
            '-' -> retVal = (lastVal.toDouble().minus(toCompute.toDouble())).toString()
            '*' -> retVal = (lastVal.toDouble().times(toCompute.toDouble())).toString()
            '/' -> retVal = (lastVal.toDouble().div(toCompute.toDouble())).toString()
        }
        return retVal.removeSuffix(".0")
    }

    private fun numberInput(oldNum: String, numToAdd: Char): String {
        if (firstUse || clearRes || oldNum == "0") {
            clearRes = false
            firstUse = false
            return numToAdd.toString()
        }
        return String.format("%s%s", oldNum, numToAdd)
    }


}
