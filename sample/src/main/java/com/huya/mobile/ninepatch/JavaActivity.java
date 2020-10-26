package com.huya.mobile.ninepatch;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import ua.anatolii.graphics.ninepatch.NinePatchGenerator;

/**
 * @author YvesCheung
 * 2020/10/23
 */
public class JavaActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.<TextView>findViewById(R.id.density_text).setText(
            "Density: " + getResources().getDisplayMetrics().density +
                ",  DensityDpi: " + getResources().getDisplayMetrics().densityDpi);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.bubble);
        int width = bitmapDrawable.getBitmap().getWidth();
        int height = bitmapDrawable.getBitmap().getHeight();

        Rect capInsets = new Rect(width / 2, height / 2, width / 2, height / 2);
        this.<ImageView>findViewById(R.id.imageView1).setImageDrawable(
            NinePatchGenerator.resizableImageWithCapInsets(bitmapDrawable, capInsets));
        this.<ImageView>findViewById(R.id.imageView2).setImageDrawable(
            NinePatchGenerator.resizableImageWithCapInsets(bitmapDrawable, capInsets));
        this.<ImageView>findViewById(R.id.imageView3).setImageDrawable(
            NinePatchGenerator.resizableImageWithCapInsets(bitmapDrawable, capInsets));
    }
}
