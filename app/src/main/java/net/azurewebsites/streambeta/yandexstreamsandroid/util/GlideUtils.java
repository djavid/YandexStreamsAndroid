package net.azurewebsites.streambeta.yandexstreamsandroid.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class GlideUtils {
    public static void loadImageIntoView(Context context, ImageView imageView, String url, int placeholderId) {
        Glide
                .with(context)
                .load(url)
                .asBitmap().centerCrop()
                .placeholder(placeholderId)
                .into(imageView);
    }
}
