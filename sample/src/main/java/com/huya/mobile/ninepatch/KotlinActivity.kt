package com.huya.mobile.ninepatch

import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ua.anatolii.graphics.ninepatch.resizableImageWithCapInsets

/**
 * @author YvesCheung
 * 2020/10/22
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmapDrawable = resources.getDrawable(R.drawable.bubble) as BitmapDrawable
        val width = bitmapDrawable.bitmap.width
        val height = bitmapDrawable.bitmap.height

        val capInsets = Rect(width / 2, height / 2, width / 2, height / 2)
        imageView1.setImageDrawable(bitmapDrawable.resizableImageWithCapInsets(capInsets))
        imageView2.setImageDrawable(bitmapDrawable.resizableImageWithCapInsets(capInsets))
        imageView3.setImageDrawable(bitmapDrawable.resizableImageWithCapInsets(capInsets))
    }
}