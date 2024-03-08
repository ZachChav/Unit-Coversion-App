package com.gamecodeschool.unitconversionapp
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputValue: EditText
    private lateinit var spinnerFrom: Spinner
    private lateinit var spinnerTo: Spinner
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputValue = findViewById(R.id.inputValue)
        spinnerFrom = findViewById(R.id.spinnerFrom)
        spinnerTo = findViewById(R.id.spinnerTo)
        resultTextView = findViewById(R.id.resultTextView)

        val units = arrayOf("Meter", "Foot", "Inch", "Celsius", "Fahrenheit", "Kelvin", "Gram", "Kilogram", "Ounce") // Example units

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        findViewById<Button>(R.id.convertButton).setOnClickListener {
            convert()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun convert() {
        val value = inputValue.text.toString().toDoubleOrNull()
        if (value == null) {
            resultTextView.text = "Invalid input"
            return
        }

        val fromUnit = spinnerFrom.selectedItemPosition
        val toUnit = spinnerTo.selectedItemPosition

        val result = when (fromUnit) {
            in 0..2 -> convertLength(value, fromUnit, toUnit)
            in 3..5 -> convertTemperature(value, fromUnit, toUnit)
            in 6..8 -> convertWeight(value, fromUnit, toUnit)
            else -> null
        }

        if (result == null || result == 0.0) {
            resultTextView.text = "Conversion not possible"
        } else {
            resultTextView.text = result.toString()
        }
    }


    private fun convertLength(value: Double, fromUnit: Int, toUnit: Int): Any {
        return when (fromUnit) {
            0 -> convertMeter(value, toUnit)
            1 -> convertFoot(value, toUnit)
            2 -> convertInch(value, toUnit)
            else -> 0.0
        }
    }

    private fun convertTemperature(value: Double, fromUnit: Int, toUnit: Int): Any {
        return when (fromUnit) {
            3 -> convertCelsius(value, toUnit)
            4 -> convertFahrenheit(value, toUnit)
            5 -> convertKelvin(value, toUnit)
            else -> 0.0
        }
    }

    private fun convertWeight(value: Double, fromUnit: Int, toUnit: Int): Any {
        return when (fromUnit) {
            6 -> convertGram(value, toUnit)
            7 -> convertKilogram(value, toUnit)
            8 -> convertOunce(value, toUnit)
            else -> 0.0
        }
    }
    // Length conversion functions

    fun convertMeter(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value // Meter to Meter
            1 -> value * 3.28084 // Meter to Foot
            2 -> value * 39.3701 // Meter to Inch
            else -> 0.0
        }
    }

    private fun convertFoot(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.3048 // Foot to Meter
            1 -> value // Foot to Foot
            2 -> value * 12 // Foot to Inch
            else -> 0.0
        }
    }

    private fun convertInch(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            0 -> value * 0.0254 // Inch to Meter
            1 -> value * 0.0833333 // Inch to Foot
            2 -> value // Inch to Inch
            else -> 0.0
        }
    }

    // Temperature conversion functions

    private fun convertCelsius(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            3 -> value // Celsius to Celsius
            4 -> (value * 9 / 5) + 32 // Celsius to Fahrenheit
            5 -> value + 273.15 // Celsius to Kelvin
            else -> 0.0
        }
    }

    private fun convertFahrenheit(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            3 -> (value - 32) * 5 / 9 // Fahrenheit to Celsius
            4 -> value // Fahrenheit to Fahrenheit
            5 -> (value + 459.67) * 5 / 9 // Fahrenheit to Kelvin
            else -> 0.0
        }
    }

    private fun convertKelvin(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            3 -> value - 273.15 // Kelvin to Celsius
            4 -> (value * 9 / 5) - 459.67 // Kelvin to Fahrenheit
            5 -> value // Kelvin to Kelvin
            else -> 0.0
        }
    }

    // Weight conversion functions

    private fun convertGram(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            6 -> value // Gram to Gram
            7 -> value * 0.001 // Gram to Kilogram
            8 -> value * 0.035274 // Gram to Ounce
            else -> 0.0
        }
    }

    private fun convertKilogram(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            6 -> value * 1000 // Kilogram to Gram
            7 -> value // Kilogram to Kilogram
            8 -> value * 35.274 // Kilogram to Ounce
            else -> 0.0
        }
    }

    private fun convertOunce(value: Double, toUnit: Int): Double {
        return when (toUnit) {
            6 -> value * 28.3495 // Ounce to Gram
            7 -> value * 0.0283495 // Ounce to Kilogram
            8 -> value // Ounce to Ounce
            else -> 0.0
        }
    }
}
