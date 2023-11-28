package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var resultTextView: TextView;
    private var currNumber: String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Global
        resultTextView = findViewById(R.id.resultTextView);

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

        var buttons = arrayListOf<Button>(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8,btn9)
        buttons.forEach { button ->
            button.setOnClickListener{
                var value = button.text
                NumberClick(value)
            }
        }

        // Functions
        var clearBtn = findViewById<Button>(R.id.buttonC)
        clearBtn.setOnClickListener { ClearTextView() }

    }

    private fun NumberClick(value: CharSequence){
        currNumber += value.toString()
        updateTextView(currNumber)
    }

    private fun ClearTextView(){
        currNumber = ""
        updateTextView("")
    }

    private fun updateTextView(text: String){
        resultTextView.text = text
    }

}