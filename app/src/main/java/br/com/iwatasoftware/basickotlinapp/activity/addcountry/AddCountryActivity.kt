package br.com.iwatasoftware.basickotlinapp.activity.addcountry

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import br.com.iwatasoftware.basickotlinapp.R
import br.com.iwatasoftware.basickotlinapp.activity.cropimage.CropImageActivity
import kotlinx.android.synthetic.main.activity_add_country.*
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class AddCountryActivity : AppCompatActivity(), AddCountryView {

    private val pickImage : Int = 1

    private lateinit var ivPreviewCountry : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_country)

        val btnFile = btn_add_file
        ivPreviewCountry = iv_preview_country

        btnFile.setOnClickListener {
//            val intent = Intent()
//            intent.action = Intent.ACTION_GET_CONTENT
//
//            intent.type = "image/*"
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), pickImage)
            startActivity(Intent(this, CropImageActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickImage) {
            if (resultCode == Activity.RESULT_OK) {
                val data1 : Uri? = data?.data
                val thumbnail = getThumbnail(data1)
                val create = RoundedBitmapDrawableFactory.create(resources, thumbnail)
                create.cornerRadius = 1000f
                ivPreviewCountry.setImageDrawable(create)
            } else {
                showToast("O bagulho deu ruim")
                ivPreviewCountry.setImageURI(null)
            }
        }
    }

    override fun showToast(texto : String) {
        br.com.iwatasoftware.basickotlinapp.util.showToast(
            this, texto, Toast.LENGTH_LONG)
    }

    @Throws(FileNotFoundException::class, IOException::class)
    fun getThumbnail(uri: Uri?): Bitmap? {
        var input: InputStream? = this.contentResolver.openInputStream(uri!!)
        val onlyBoundsOptions = BitmapFactory.Options()
        onlyBoundsOptions.inJustDecodeBounds = true
        onlyBoundsOptions.inDither = true //optional
        onlyBoundsOptions.inPreferredConfig = Bitmap.Config.ARGB_8888 //optional
        BitmapFactory.decodeStream(input, null, onlyBoundsOptions)
        input?.close()
        if (onlyBoundsOptions.outWidth == -1 || onlyBoundsOptions.outHeight == -1) {
            return null
        }
        val originalSize =
            if (onlyBoundsOptions.outHeight > onlyBoundsOptions.outWidth) onlyBoundsOptions.outHeight else onlyBoundsOptions.outWidth
        var ratio: Double
        ratio = if (originalSize > 128.0) {
            originalSize / 128.0
        } else {
            1.0
        }
        val bitmapOptions = BitmapFactory.Options()
        bitmapOptions.inSampleSize = getPowerOfTwoForSampleRatio(ratio)
        bitmapOptions.inDither = true //optional
        bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888 //
        input = this.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions)
        input?.close()
        return bitmap
    }

    private fun getPowerOfTwoForSampleRatio(ratio: Double): Int {
        val k = Integer.highestOneBit(Math.floor(ratio).toInt())
        return if (k == 0) 1 else k
    }
}
