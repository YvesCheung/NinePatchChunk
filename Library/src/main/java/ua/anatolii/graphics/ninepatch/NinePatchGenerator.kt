@file:JvmName(name = "NinePatchGenerator")

package ua.anatolii.graphics.ninepatch

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.NinePatch
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.NinePatchDrawable
import ua.anatolii.graphics.ninepatch.NinePatchChunk.setupColors
import kotlin.math.max
import kotlin.math.roundToInt

/**
 * @author YvesCheung
 * 2020/10/22
 */
@JvmOverloads
fun BitmapDrawable.resizableImageWithCapInsets(
    capInsets: Rect,
    res: Resources = Resources.getSystem(),
    srcName: String? = null
):
    NinePatchDrawable {
    if (bitmap == null) {
        throw NullPointerException("BitmapDrawable.bitmap is null!")
    }
    return bitmap.resizableImageWithCapInsets(capInsets, res, srcName)
}

@JvmOverloads
fun Bitmap.resizableImageWithCapInsets(
    capInsets: Rect,
    res: Resources = Resources.getSystem(),
    srcName: String? = null
): NinePatchDrawable {
    val originChunk = this.ninePatchChunk
    if (originChunk != null) {
        if (NinePatch.isNinePatchChunk(originChunk)) {
            val chunk = BitmapType.NinePatch.createChunk(this)
            return NinePatchDrawable(res, this, originChunk, chunk.padding, srcName)
        }
    }
    val copyCapInsets = Rect(capInsets)
    if (copyCapInsets.right < copyCapInsets.left)
        throw IllegalArgumentException(
            "capInsets's right should be larger than left, capInsets = $copyCapInsets")
    if (copyCapInsets.top > copyCapInsets.bottom)
        throw IllegalArgumentException(
            "capInsets's bottom should be larger than top, capInsets = $copyCapInsets")

    copyCapInsets.right = max(copyCapInsets.right, copyCapInsets.left + 1)
    copyCapInsets.bottom = max(copyCapInsets.bottom, copyCapInsets.top + 1)

    if (copyCapInsets.left < 0 || copyCapInsets.right >= this.width)
        throw IllegalArgumentException(
            "left..right should be in range[0,${this.width - 1}], capInsets = $copyCapInsets")
    if (copyCapInsets.top < 0 || copyCapInsets.bottom >= this.height)
        throw IllegalArgumentException(
            "top..bottom should be in range[0,${this.height - 1}], capInsets = $copyCapInsets")

    val targetDensity = res.displayMetrics.densityDpi
    val bitmap = resizeBitmapWithDensity(this, copyCapInsets, targetDensity)

    val newChunk = NinePatchChunk.createEmptyChunk()
    newChunk.xDivs = arrayListOf(Div(copyCapInsets.left, copyCapInsets.right))
    newChunk.yDivs = arrayListOf(Div(copyCapInsets.top, copyCapInsets.bottom))
    setupColors(bitmap, bitmap.width, bitmap.height, newChunk)
    return NinePatchDrawable(res, bitmap, newChunk.toBytes(), newChunk.padding, srcName)
}

private fun resizeBitmapWithDensity(bitmap: Bitmap, capInsets: Rect, targetDensity: Int): Bitmap {
    val densityChange: Float = targetDensity.toFloat() / bitmap.density
    if (densityChange != 1f) {

        capInsets.left = (capInsets.left * densityChange).roundToInt()
        capInsets.right = (capInsets.right * densityChange).roundToInt()
        capInsets.top = (capInsets.top * densityChange).roundToInt()
        capInsets.bottom = (capInsets.bottom * densityChange).roundToInt()

        val newBitmap = Bitmap.createScaledBitmap(
            bitmap,
            (bitmap.width * densityChange).roundToInt(),
            (bitmap.height * densityChange).roundToInt(),
            true
        )
        newBitmap.density = targetDensity
        return newBitmap
    }
    return bitmap
}
