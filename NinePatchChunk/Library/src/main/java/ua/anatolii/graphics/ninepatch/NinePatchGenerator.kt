@file:JvmName(name = "NinePatchGenerator")

package ua.anatolii.graphics.ninepatch

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.NinePatch
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.NinePatchDrawable
import ua.anatolii.graphics.ninepatch.NinePatchChunk.setupColors

/**
 * @author YvesCheung
 * 2020/10/22
 */
@JvmOverloads
fun BitmapDrawable.resizableImageWithCapInsets(
    capInsets: Rect,
    res: Resources? = null,
    srcName: String? = null
):
    NinePatchDrawable {
    if (bitmap == null) {
        throw NullPointerException("BitmapDrawable.bitmap is null!")
    }
    return bitmap.resizableImageWithCapInsets(capInsets)
}

@JvmOverloads
fun Bitmap.resizableImageWithCapInsets(
    capInsets: Rect,
    res: Resources? = null,
    srcName: String? = null
): NinePatchDrawable {
    val originChunk = this.ninePatchChunk
    if (originChunk != null) {
        if (NinePatch.isNinePatchChunk(originChunk)) {
            val chunk = BitmapType.NinePatch.createChunk(this)
            return NinePatchDrawable(res, this, originChunk, chunk.padding, srcName)
        }
    }

    if (capInsets.right < capInsets.left)
        throw IllegalArgumentException(
            "capInsets's right should be larger than left, capInsets = $capInsets")
    if (capInsets.top > capInsets.bottom)
        throw IllegalArgumentException(
            "capInsets's bottom should be larger than top, capInsets = $capInsets")
    if (capInsets.left < 0 || capInsets.right >= this.width)
        throw IllegalArgumentException(
            "left..right should be in range[0,${this.width - 1}], capInsets = $capInsets")
    if (capInsets.top < 0 || capInsets.bottom >= this.height)
        throw IllegalArgumentException(
            "top..bottom should be in range[0,${this.height - 1}], capInsets = $capInsets")

    val newChunk = NinePatchChunk.createEmptyChunk()
    newChunk.xDivs = arrayListOf(Div(capInsets.left, capInsets.right))
    newChunk.yDivs = arrayListOf(Div(capInsets.top, capInsets.bottom))
    setupColors(this, this.width, this.height, newChunk)
    return NinePatchDrawable(res, this, newChunk.toBytes(), newChunk.padding, srcName)
}
