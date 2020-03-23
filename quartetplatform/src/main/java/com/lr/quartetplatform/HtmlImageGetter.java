package com.lr.quartetplatform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.text.Html;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class HtmlImageGetter implements Html.ImageGetter {
    private Context context;
    private TextView textView;
    private boolean isShowImage;

    public HtmlImageGetter(Context context, TextView textView, boolean isShowImage) {
        this.context = context;
        this.textView = textView;
        this.isShowImage = isShowImage;
    }

    @Override
    public Drawable getDrawable(String s) {
        final LevelListDrawable drawable = new LevelListDrawable();
        Glide.with(context)
                .asBitmap()
                .load(s)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        if (isShowImage) {
                            int width = textView.getWidth();
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(resource);
                            float scale = width * 1.0f / resource.getWidth();
                            int afterWidth = (int) (resource.getWidth() * scale);
                            int afterHeight = (int) (resource.getHeight() * scale);
                            Bitmap bitmap = resizeBitmap(resource, afterWidth, afterHeight);
                            drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
                            drawable.addLevel(1, 1, bitmapDrawable);
                            drawable.setLevel(1);
                        }
                        textView.invalidate();
                        textView.setText(textView.getText());
                    }
                });
        return drawable;
    }

    public static Bitmap resizeBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float scaleWidth = ((float) w) / width;
        float scaleHeight = ((float) h) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }
}
