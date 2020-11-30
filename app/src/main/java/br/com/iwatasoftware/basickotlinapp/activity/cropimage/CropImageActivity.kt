package br.com.iwatasoftware.basickotlinapp.activity.cropimage

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.iwatasoftware.basickotlinapp.R
import com.google.android.material.snackbar.Snackbar
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_crop_image.*

class CropImageActivity : AppCompatActivity() {

    private lateinit var crop: CropImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)

        supportActionBar?.setTitle(R.string.recorte_foto)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        crop = img_crop

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        img_crop.layoutParams.height = width
        img_crop.layoutParams.width = width
        img_crop.background = null
        img_crop.scaleType = CropImageView.ScaleType.FIT_CENTER
        val icon = BitmapFactory.decodeResource(resources, R.drawable.teste)
        img_crop.setImageBitmap(icon)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cropped_image, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_crop_image_save -> {
                Snackbar.make(crop.rootView, "teste", Snackbar.LENGTH_LONG).show()
            }
        }
        return true
    }
}
