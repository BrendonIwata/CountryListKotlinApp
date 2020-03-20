package br.com.iwatasoftware.basickotlinapp.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.iwatasoftware.basickotlinapp.R
import kotlinx.android.synthetic.main.activity_add_country.*

class AddCountryActivity : AppCompatActivity() {

    val PICK_IMAGE : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)

        val btnFile = btn_add_file

        btnFile.setOnClickListener {
            val intent = Intent();
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"

            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }
    }
}
