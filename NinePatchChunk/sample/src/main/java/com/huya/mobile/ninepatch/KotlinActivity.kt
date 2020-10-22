package com.huya.mobile.ninepatch

import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import ua.anatolii.graphics.ninepatch.Div
import ua.anatolii.graphics.ninepatch.NinePatchChunk
import ua.anatolii.graphics.ninepatch.resizableImageWithCapInsets

/**
 * @author YvesCheung
 * 2020/10/22
 */
class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val chunk = resources.assets.open("bubble_ninepatch.9.png").use {
            NinePatchChunk.createChunkFromRawBitmap(this, it).chunk
        }
        val image = resources.assets.open("bubble_ninepatch.9.png").use {
            NinePatchChunk.create9PatchDrawable(this, it, null)
        }
        Log.i("Yves", chunk.toString())

        val bitmapDrawable = resources.getDrawable(R.drawable.bubble) as BitmapDrawable
        val width = bitmapDrawable.bitmap.width
        val height = bitmapDrawable.bitmap.height
        val capInsets = Rect(width / 2 + 1, height / 2 - 2, width / 2 + 7, height / 2 + 7)
        val newChunk = NinePatchChunk.createEmptyChunk()
        newChunk.xDivs = arrayListOf(Div(capInsets.left, capInsets.right))
        newChunk.yDivs = arrayListOf(Div(capInsets.top, capInsets.bottom))
        NinePatchChunk.setupColors(bitmapDrawable.bitmap, bitmapDrawable.bitmap.width, bitmapDrawable.bitmap.height, newChunk)
        Log.i("Yves", newChunk.toString())

        val ninePatchDrawable =
            bitmapDrawable.resizableImageWithCapInsets(capInsets)
        imageView1.setImageDrawable(image)
        imageView2.setImageDrawable(image)
        imageView3.setImageDrawable(image)
    }
}