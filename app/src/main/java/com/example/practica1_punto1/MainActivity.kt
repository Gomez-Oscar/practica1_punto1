package com.example.practica1_punto1

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var fecha = ""
    private var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, month)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val format = "MM/dd/yyyy"
                val simpleDateFormat = SimpleDateFormat(format, Locale.US)
                fecha = simpleDateFormat.format(cal.time).toString()
                tv_fecha_nacimiento.text = fecha
            }

        ibt_calendario.setOnClickListener {

            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

        bt_guardar.setOnClickListener {

            val nombre = et_nombre.text.toString()
            val correo = et_correo.text.toString()
            val telefono = et_telefono.text.toString()
            val contrasena = et_contrasena.text.toString()
            val contrasenax2 = et_contrasenax2.text.toString()
            val genero = if (rb_masculino.isChecked) "Masculino" else "Femenino"
            var hobbies = ""
            val ciudad = sp_ciudad.selectedItem.toString()

            if (cb_hobbie1.isChecked) hobbies = "$hobbies Montar en bicicleta"
            if (cb_hobbie2.isChecked) hobbies = "$hobbies Jugar fútbol"
            if (cb_hobbie3.isChecked) hobbies = "$hobbies Tocar guitarra"
            if (cb_hobbie4.isChecked) hobbies = "$hobbies Practicar bungee jumping"


            if (contrasena == contrasenax2) {
                if (nombre.isEmpty() || nombre.isBlank()) {
                    tv_info.text = " No se ha ingresado ningún nombre"
                } else if (correo.isEmpty() || correo.isBlank()) {
                    tv_info.text = "No se ha ingresado ningún correo"
                } else if (telefono.isEmpty() || telefono.isBlank()) {
                    tv_info.text = "No se ha ingresado ningún teléfono"
                } else if (contrasena.isEmpty() || contrasena.isBlank()) {
                    tv_info.text = "No se ha ingresado ninguna contraseña"
                } else if (ciudad.isEmpty()) {
                    tv_info.text = "No se ha ingresado ninguna ciudad"
                } else if(fecha.isEmpty()){
                    tv_info.text = "No se ha ingresado ninguna fecha de nacimiento"
                }else{
                    tv_info.text = "Nombre: $nombre \nCorreo: $correo \nTeléfono: $telefono " +
                            "\nCiudad de nacimiento: $ciudad \nFecha de nacimiento: $fecha \nSexo: $genero"

                    if (hobbies.isNotEmpty()) {
                        tv_info.append("\nHobbies: $hobbies")
                    }

                }

            } else {
                tv_info.text = "Las contraseñas NO coniciden"
            }

        }

    }

}


