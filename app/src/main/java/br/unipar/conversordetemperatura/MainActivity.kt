package br.unipar.conversordetemperatura

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var resultadoCelsius: String? = null
    private var resultadoFahrenheit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edTemperatura = findViewById<EditText>(R.id.edTemperatura)
        val btnCel = findViewById<Button>(R.id.btnCel)
        val btnFah = findViewById<Button>(R.id.btnFah)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val resultado = findViewById<TextView>(R.id.txtResultado)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnCel.setOnClickListener {
            val temperaturaC = edTemperatura.text.toString()
            if (temperaturaC.isNotEmpty()) {
                val celsius = temperaturaC.toFloat()
                resultadoCelsius = "$celsius °C es igual a ${(celsius * 9 / 5) + 32} °F"
            } else {
                resultadoCelsius = "Número inválido."
            }
        }

        btnFah.setOnClickListener {
            val temperaturaF = edTemperatura.text.toString()
            if (temperaturaF.isNotEmpty()) {
                val fahrenheit = temperaturaF.toFloat()
                resultadoFahrenheit = "$fahrenheit °F es igual a ${(fahrenheit - 32) * 5 / 9} °C"
            } else {
                resultadoFahrenheit = "Número inválido."
            }
        }

        btnConvert.setOnClickListener {
            // Limpeza dos campos
            edTemperatura.text.clear()
            resultado.text = ""


            resultado.text = resultadoCelsius ?: resultadoFahrenheit ?: "ninguna conversion realizada."

            // Limpar las variables
            resultadoCelsius = null
            resultadoFahrenheit = null
        }
    }
}
