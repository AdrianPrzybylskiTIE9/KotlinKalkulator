package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt
import kotlin.reflect.typeOf

class MainActivity : AppCompatActivity() {

    lateinit var resultTextView: TextView;
    lateinit var prevTextView: TextView;
    private var prevNumber: String = "";
    private var currNumber: String = "0";
    private var operator: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Global
        resultTextView = findViewById(R.id.resultTextView);
        prevTextView = findViewById(R.id.prevTextView);

        // Numbers
        var btn0 = findViewById<Button>(R.id.button0)
        var btn1 = findViewById<Button>(R.id.button1)
        var btn2 = findViewById<Button>(R.id.button2)
        var btn3 = findViewById<Button>(R.id.button3)
        var btn4 = findViewById<Button>(R.id.button4)
        var btn5 = findViewById<Button>(R.id.button5)
        var btn6 = findViewById<Button>(R.id.button6)
        var btn7 = findViewById<Button>(R.id.button7)
        var btn8 = findViewById<Button>(R.id.button8)
        var btn9 = findViewById<Button>(R.id.button9)
        var btnDot = findViewById<Button>(R.id.buttonDot)

        var buttons = arrayListOf<Button>(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8,btn9, btnDot)
        buttons.forEach { button ->
            button.setOnClickListener{
                var value = button.text
                numberClick(value)
            }
        }

        // Functions
        var clearBtn = findViewById<Button>(R.id.buttonC)
        var rootBtn = findViewById<Button>(R.id.buttonRoot)
        var squareBtn = findViewById<Button>(R.id.buttonSquare)
        var divideBtn = findViewById<Button>(R.id.buttonDivide)
        var multiplyBtn = findViewById<Button>(R.id.buttonMultiply)
        var minusBtn = findViewById<Button>(R.id.buttonMinus)
        var plusBtn = findViewById<Button>(R.id.buttonPlus)
        var equalsBtn = findViewById<Button>(R.id.buttonEquals)
        var plusMinusBtn = findViewById<Button>(R.id.buttonPlusMinus)

        var operatorButtons = arrayListOf<Button>(rootBtn, squareBtn, divideBtn, multiplyBtn, minusBtn, plusBtn)
        operatorButtons.forEach { button ->
            button.setOnClickListener{
                var buttonOperator = button.text.toString()
                operatorClick(buttonOperator)
            }
        }

        clearBtn.setOnClickListener { clearTextView() }
        equalsBtn.setOnClickListener { calculate() }
        squareBtn.setOnClickListener { square() }
        rootBtn.setOnClickListener { root() }
        plusMinusBtn.setOnClickListener { convertSign() }

    }

    private fun numberClick(value: CharSequence){
        if (value == "." && currNumber.contains(".")) return
        else if ((currNumber == "0" && value != ".")) {
            currNumber = value.toString()
        }else{
            currNumber += value.toString()
        }
        updateTextView(currNumber, prevNumber)
    }

    private fun operatorClick(op: String){
        if(currNumber.isNotEmpty()){
            operator = op;
            prevNumber = currNumber;
            currNumber = ""
        }
    }

    private fun calculate(){
        if(prevNumber.isNotEmpty() || currNumber.isNotEmpty()){
            val num1 = if(prevNumber.isNotEmpty()) prevNumber.toDouble() else currNumber.toDouble()
            val num2 = if(currNumber.isNotEmpty()) currNumber.toDouble() else prevNumber.toDouble()



            var result: Double = 0.0

            when(operator){
                "+" -> result = num1 + num2
                "-" -> result = num1 - num2
                "x" -> result = num1 * num2
                "/" -> result = num1 / num2
            }

            prevNumber = num1.toString()
            currNumber = result.toString()
            updateTextView(currNumber, prevNumber)
        }
    }

    private fun square(){
        if(currNumber.isNotEmpty()){
            var num = currNumber.toDouble()
            var result = num * num
            currNumber = result.toString()
            updateTextView(currNumber, prevNumber)

        }
    }

    private fun root(){
        if(currNumber.isNotEmpty()){
            var num = currNumber.toDouble()
            var result = sqrt(num)
            currNumber = result.toString()
            updateTextView(currNumber.toString(), prevNumber)
        }
    }

    private fun clearTextView(){
        currNumber = "0"
        prevNumber=""
        updateTextView("0", "")
    }

    private fun convertSign(){
        if(currNumber.isNotEmpty()){
            var num = currNumber.toDouble()
            currNumber = if(num != 0.0) (-num).toString() else currNumber
            updateTextView(currNumber, prevNumber)
        }
    }

    private fun updateTextView(value: String, prevValue: String){
        var maxLength = 11
        if((value.length > maxLength && prevValue.length > maxLength)){
            val truncatedText = value.substring(0, maxLength)
            val truncatedPrevText = prevValue.substring(0, maxLength)
            resultTextView.text = truncatedText
            prevTextView.text = truncatedPrevText
            return
        }
        resultTextView.text = value
        prevTextView.text = "$prevValue$operator"
    }

}