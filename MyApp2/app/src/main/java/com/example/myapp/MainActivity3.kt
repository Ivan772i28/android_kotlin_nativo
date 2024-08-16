package com.example.myapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity3 : AppCompatActivity() {

    private lateinit var textViewInput: TextView
    private lateinit var textViewOutput: TextView

    private var inputStr: StringBuilder = StringBuilder()
    private var currentOperator: String? = null
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val bt=findViewById<Button>(R.id.btnn)
        bt.setOnClickListener{
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        textViewInput = findViewById(R.id.textViewInput)
        textViewOutput = findViewById(R.id.textViewOutput)

        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDot: Button = findViewById(R.id.buttonDot)

        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)

        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonClear: Button = findViewById(R.id.buttonClear)

        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonDot.setOnClickListener { appendDot() }

        buttonAdd.setOnClickListener { setOperator("+") }
        buttonSubtract.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }

        buttonEquals.setOnClickListener { evaluateExpression() }
        buttonClear.setOnClickListener { clearCalculator() }
    }

    private fun appendNumber(number: String) {
        inputStr.append(number)
        updateInputTextView()
    }

    private fun appendDot() {
        if (!inputStr.contains('.')) {
            if (inputStr.isEmpty()) {
                inputStr.append("0.")
            } else {
                inputStr.append(".")
            }
            updateInputTextView()
        }
    }

    private fun setOperator(operator: String) {
        if (inputStr.isNotEmpty()) {
            operand1 = inputStr.toString().toDouble()
            currentOperator = operator
            clearInput()
            updateInputTextView()
        }
    }

    private fun evaluateExpression() {
        if (inputStr.isNotEmpty() && currentOperator != null) {
            operand2 = inputStr.toString().toDouble()
            var result: Double = when (currentOperator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                "/" -> operand1 / operand2
                else -> 0.0
            }
            clearInput()
            inputStr.append(result)
            updateOutputTextView()
        }
    }

    private fun clearCalculator() {
        inputStr.clear()
        currentOperator = null
        operand1 = 0.0
        operand2 = 0.0
        updateInputTextView()
        updateOutputTextView()
    }

    private fun clearInput() {
        inputStr.clear()
    }

    private fun updateInputTextView() {
        textViewInput.text = inputStr.toString()
    }

    private fun updateOutputTextView() {
        textViewOutput.text = inputStr.toString()
    }
}
