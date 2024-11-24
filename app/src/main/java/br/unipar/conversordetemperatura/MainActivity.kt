package br.unipar.conversordetemperatura

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ajustes de padding dinámico
        setupInsets()

        // Referencias a vistas
        val edTemperatura = findViewById<EditText>(R.id.edTemperatura)
        val btnCel = findViewById<RadioButton>(R.id.btnCel)
        val btnFah = findViewById<RadioButton>(R.id.btnFah)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val resultado = findViewById<TextView>(R.id.txtResultado)

        // Manejadores para alternar selección de los RadioButtons
        var selectedRadio: RadioButton? = null
        btnCel.setOnClickListener {
            if (btnCel.isChecked && selectedRadio == btnCel) {
                btnCel.isChecked = false
                selectedRadio = null
            } else {
                selectedRadio = btnCel
            }
        }

        btnFah.setOnClickListener {
            if (btnFah.isChecked && selectedRadio == btnFah) {
                btnFah.isChecked = false
                selectedRadio = null
            } else {
                selectedRadio = btnFah
            }
        }

        // Botón para convertir según la opción seleccionada
        btnConvert.setOnClickListener {
            val input = edTemperatura.text.toString().trim()
            if (input.isNotEmpty() && input.isNumeric()) {
                val temp = input.toFloat()
                val conversionResult = when (selectedRadio) {
                    btnCel -> "%.2f °C es igual a %.2f °F".format(temp, (temp * 9 / 5) + 32)
                    btnFah -> "%.2f °F es igual a %.2f °C".format(temp, (temp - 32) * 5 / 9)
                    else -> "Por favor, selecciona una opción de conversión."
                }
                resultado.text = conversionResult
            } else {
                resultado.text = "Por favor, ingresa un número válido."
            }
        }
    }

    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Extensión para verificar si un string es un número válido
    private fun String.isNumeric(): Boolean {
        return this.toFloatOrNull() != null
    }
}