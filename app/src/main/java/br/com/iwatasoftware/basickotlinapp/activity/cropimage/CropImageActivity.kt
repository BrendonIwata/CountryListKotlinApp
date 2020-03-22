package br.com.iwatasoftware.basickotlinapp.activity.cropimage

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import br.com.iwatasoftware.basickotlinapp.R
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_crop_image.*

class CropImageActivity : AppCompatActivity() {

    private var isCroped = false
    private lateinit var crop : CropImageView
    private lateinit var btnContinuar : Button
    private lateinit var btnCancelar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_image)

        crop = img_crop
        btnContinuar = btn_done
        btnCancelar = btn_cancel

        var displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        width = width - 80
        img_crop.layoutParams.height = width
        img_crop.layoutParams.width = width
        img_crop.background = null
        img_crop.scaleType = CropImageView.ScaleType.FIT_CENTER
        var icon = BitmapFactory.decodeResource(resources, R.drawable.teste)
        img_crop.setImageBitmap(icon)
    }
}
