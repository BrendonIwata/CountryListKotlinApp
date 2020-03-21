package br.com.iwatasoftware.basickotlinapp.util

import android.content.Context
import android.widget.Toast

fun showToast (context: Context, texto : String, length : Int) {
    Toast.makeText(context, texto, length).show()
}
