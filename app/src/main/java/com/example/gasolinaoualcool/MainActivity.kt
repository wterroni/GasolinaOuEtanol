package com.example.gasolinaoualcool

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickBotao()
    }

    private fun clickBotao() {
        calcularButton.setOnClickListener {
            if (validarCampos()) {
                calculaPreco()
            }
        }
    }

    private fun validarCampos(): Boolean {
        var message = ""
        when {
            etanolEditText.text.isEmpty() && gasolinaEditText.text.isNotEmpty() -> message = getString(R.string.error_etanol)
            etanolEditText.text.isEmpty() && gasolinaEditText.text.isEmpty() -> message = getString(R.string.message_error)
            gasolinaEditText.text.isEmpty() -> message = getString(R.string.label_gasolina)
        }
        return if (message.isNotEmpty()) {
            exibeMensagemCustomizada(message)
            false
        } else {
            true
        }
    }

    private fun exibeMensagem(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun exibeMensagemCustomizada(message:String) {
        warningView.visibility = View.VISIBLE
        warningTextView.visibility = View.VISIBLE
        warningTextView.text = message
    }

    private fun calculaPreco() {
        val valorGasolina = gasolinaEditText.text.toString().toDouble()
        val valorEtanol = etanolEditText.text.toString().toDouble()
        val calculo = valorGasolina * 0.70
        if (valorEtanol <= calculo){
            exibeMensagemCustomizada(getString(R.string.message_etanol))
        } else if (valorEtanol > calculo) {
            exibeMensagemCustomizada(getString(R.string.message_gasolina))
        }
    }
}
