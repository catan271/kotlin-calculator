package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import net.objecthunter.exp4j.ExpressionBuilder

fun solveMathOperation(operation: String): Double {
    return ExpressionBuilder(operation).build().evaluate()
}

class MainActivity : ComponentActivity() {
    private lateinit var pre: TextView
    private lateinit var current: TextView
    private lateinit var op: TextView

    private var currentNumber: String = ""
    private var currentResult: Double = 0.0

    private var isFirstEvaluation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pre = findViewById(R.id.pre)
        current = findViewById(R.id.current)
        op = findViewById(R.id.op)

        val button0: Button = findViewById(R.id.button_0)
        val button1: Button = findViewById(R.id.button_1)
        val button2: Button = findViewById(R.id.button_2)
        val button3: Button = findViewById(R.id.button_3)
        val button4: Button = findViewById(R.id.button_4)
        val button5: Button = findViewById(R.id.button_5)
        val button6: Button = findViewById(R.id.button_6)
        val button7: Button = findViewById(R.id.button_7)
        val button8: Button = findViewById(R.id.button_8)
        val button9: Button = findViewById(R.id.button_9)

        val buttonClear: Button = findViewById(R.id.button_clear)
        val buttonBackSpace: Button = findViewById(R.id.button_backspace)
        val buttonCE: Button = findViewById(R.id.button_ce)
        val buttonDivision: Button = findViewById(R.id.button_division)
        val buttonMultiply: Button = findViewById(R.id.multiply)
        val buttonMinus: Button = findViewById(R.id.button_minus)
        val buttonPlus: Button = findViewById(R.id.button_plus)
        val buttonEqual: Button = findViewById(R.id.button_equal)
        val buttonComma: Button = findViewById(R.id.button_comma)
        val buttonNeg: Button = findViewById(R.id.button_neg)


        val numberButtons = listOf(
            button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9
        )

        numberButtons.forEach { button ->
            button.setOnClickListener {
                current.text = current.text.toString() + button.text
            }
        }

        buttonPlus.setOnClickListener { operate("+") }
        buttonMinus.setOnClickListener { operate("-") }
        buttonMultiply.setOnClickListener { operate("*") }
        buttonDivision.setOnClickListener { operate("/") }

        buttonEqual.setOnClickListener {
            calc()
        }

        buttonClear.setOnClickListener {
            pre.text = ""
            op.text = ""
            current.text = ""
        }

        buttonBackSpace.setOnClickListener {
            if (current.text.isNotEmpty()) {
                current.text = current.text.dropLast(1)
            }
        }

        buttonCE.setOnClickListener {
            current.text = ""
        }

        buttonComma.setOnClickListener {
            if (current.text.isNotEmpty() && current.text.last().isDigit() && !current.text.contains(".")) {
                current.text = current.text.toString() + "."
            }
        }

        buttonNeg.setOnClickListener {
            if (current.text.isNotEmpty()) {
                if( current.text.first() != '-') {
                    current.text = '-' + current.text.toString()
                } else {
                    current.text = current.text.drop(1)
                }
            } else {
                current.text = '-' + current.text.toString()
            }
        }
    }

    private fun operate(newOp: String) {
        if (current.text.isNotEmpty()) {
            if (pre.text.isNotEmpty()) {
                val exp = pre.text.toString() + op.text.toString() + current.text.toString()
                var res = ExpressionBuilder(exp).build().evaluate();
                if (res.toInt().compareTo(res) == 0) {
                    pre.text = res.toInt().toString()
                } else {
                    pre.text = res.toString()
                }
            } else {
                pre.text = current.text
            }
            op.text = newOp
            current.text = ""
        }
    }

    private fun calc() {
        if (pre.text.isNotEmpty() && current.text.isNotEmpty()) {
            val exp = pre.text.toString() + op.text.toString() + current.text.toString()
            var res = ExpressionBuilder(exp).build().evaluate();
            if (res.toInt().compareTo(res) == 0) {
                current.text = res.toInt().toString()
            } else {
                current.text = res.toString()
            }
            pre.text = ""
            op.text = ""
        }
    }
}
