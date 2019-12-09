// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import java.util.Iterator;
import com.bumptech.glide.load.resource.bitmap.ExifInterfaceImageHeaderParser;
import com.bumptech.glide.load.ImageHeaderParser;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import okhttp3.Call;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import java.io.InputStream;
import com.bumptech.glide.load.model.GlideUrl;
import okhttp3.OkHttpClient;
import com.bumptech.glide.Registry;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import android.content.Context;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule;
import com.bumptech.glide.annotation.Excludes;
import com.bumptech.glide.module.AppGlideModule;

@Excludes({ OkHttpLibraryGlideModule.class })
// @GlideModule
public class MyAppGlideModule extends AppGlideModule
{
    public void registerComponents(@NonNull final Context context, @NonNull final Glide glide, @NonNull final Registry registry) {
        super.registerComponents(context, glide, registry);
        final OkHttpClient okHttpClient = new OkHttpClient();
        registry.replace((Class)GlideUrl.class, (Class)InputStream.class, (ModelLoaderFactory)new OkHttpUrlLoader.Factory((Call.Factory)okHttpClient));
        for (final ImageHeaderParser parser : registry.getImageHeaderParsers()) {
            if (parser instanceof ExifInterfaceImageHeaderParser) {
                registry.getImageHeaderParsers().remove(parser);
            }
        }
    }
}
