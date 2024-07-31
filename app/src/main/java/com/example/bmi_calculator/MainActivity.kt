package com.example.bmi_calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weight = findViewById<EditText>(R.id.enterWeightValue)
        val height = findViewById<EditText>(R.id.enterHeightValue)
        val bmicalculate = findViewById<Button>(R.id.calculate)

        bmicalculate.setOnClickListener {

            val weightvalue = weight.text.toString()
            val heightvalue = height.text.toString()
            fun validateInput(weightvalue: String?, heightvalue: String?): Boolean {
                return when{
                    weightvalue.isNullOrEmpty() -> {
                        Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                        return false
                    }
                    heightvalue.isNullOrEmpty() -> {
                        Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                        return false
                    }
                    else -> {
                        return true
                    }
                }

            }
            if(validateInput(weightvalue, heightvalue)){
                val bmi = weightvalue.toFloat() / ((heightvalue.toFloat() / 100) * (heightvalue.toFloat() / 100))
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
            }
        }

    }

    private fun displayResult(bmi2digits: Float) {
        val resultIndex = findViewById<TextView>(R.id.tvResultDisplay)
        val resultStatus = findViewById<TextView>(R.id.resultStatus)
        var message = findViewById<TextView>(R.id.message_for_user)

        resultIndex.text = bmi2digits.toString()
        message.text = "(Normal Range is 18.5 - 24.9)"

        var resultText = " "
        var color = 0

        when {
            bmi2digits < 18.50 -> {
                resultText = "Underweight"
                color = R.color.under_weight
            }
            bmi2digits in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.heathy
            }
            bmi2digits in 25.00..29.99 -> {
                resultText = "Overweight"
                color = R.color.over_weight
            }
            bmi2digits > 29.99 -> {
                resultText = "Obsese"
                color = R.color.obese
            }
        }
        resultStatus.setTextColor(ContextCompat.getColor(this, color))
        resultStatus.text = resultText

    }
}