package com.huya.mobile.ninepatch

import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ua.anatolii.graphics.ninepatch.resizableImageWithCapInsets
import kotlin.math.roundToInt

/**
 * @author YvesCheung
 * 2020/10/22
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmapDrawable = resources.getDrawable(R.drawable.bubble) as BitmapDrawable
        var content = bitmapDrawable.bitmap
        var width = bitmapDrawable.bitmap.width
        var height = bitmapDrawable.bitmap.height
        val targetDensity = resources.displayMetrics.densityDpi
        val densityChange: Float = targetDensity.toFloat() / content.density
        if (densityChange != 1f) {
            width = (content.width * densityChange).roundToInt()
            height = (content.height * densityChange).roundToInt()
            content = Bitmap.createScaledBitmap(content, width, height, true)
            content.density = targetDensity
        }

        val capInsets = Rect(width / 2, height / 2, width / 2, height / 2)
        imageView1.setImageDrawable(content.resizableImageWithCapInsets(capInsets))
        imageView2.setImageDrawable(content.resizableImageWithCapInsets(capInsets))
        imageView3.setImageDrawable(content.resizableImageWithCapInsets(capInsets))
    }
}