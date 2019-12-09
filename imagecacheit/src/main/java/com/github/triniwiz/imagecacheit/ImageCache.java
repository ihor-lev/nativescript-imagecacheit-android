// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageCache {
    static RequestManager manager;
    static Glide glide;
    static ExecutorService executorService;

    public static void init(final Context context) {
        if (ImageCache.manager == null) {
            ImageCache.manager = Glide.with(context);
        }
        if (ImageCache.glide == null) {
            ImageCache.glide = Glide.get(context);
        }
        if (ImageCache.executorService == null) {
            ImageCache.executorService = Executors.newSingleThreadExecutor();
        }
    }

    @SuppressLint({"CheckResult"})
    public static void hasItem(final String url, @Nullable final Callback callback) {
        final RequestOptions requestOptions = new RequestOptions();
        requestOptions.onlyRetrieveFromCache(true);
        ImageCache.manager.asFile().load(url).apply((BaseRequestOptions) requestOptions).listener((RequestListener) new RequestListener<File>() {
            public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<File> target, final boolean isFirstResource) {
                if (callback != null) {
                    callback.onError(e);
                }
                return false;
            }

            public boolean onResourceReady(final File resource, final Object model, final Target<File> target, final DataSource dataSource, final boolean isFirstResource) {
                if (callback != null) {
                    callback.onSuccess(resource.getAbsolutePath());
                }
                return false;
            }
        }).submit();
    }

    public static void getItem(final String url, @Nullable final Map<String, String> options, @Nullable final Callback callback) {
        final RequestOptions requestOptions = new RequestOptions();
        ImageCache.manager.asFile().load(url).apply((BaseRequestOptions) requestOptions).listener((RequestListener) new RequestListener<File>() {
            public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<File> target, final boolean isFirstResource) {
                if (callback != null) {
                    callback.onError(e);
                }
                return false;
            }

            public boolean onResourceReady(final File resource, final Object model, final Target<File> target, final DataSource dataSource, final boolean isFirstResource) {
                if (callback != null) {
                    callback.onSuccess(resource.getAbsolutePath());
                }
                return false;
            }
        }).submit();
    }

    public static void storeItem(final String url, @Nullable final String headerName, final String headerValue, @Nullable final Map<String, String> options, @Nullable final Callback callback) {
        final RequestOptions requestOptions = new RequestOptions();
        GlideUrl glideUrl = null;

        if (headerName != null && headerValue != null) {
            glideUrl = new GlideUrl(url, new LazyHeaders.Builder().addHeader(headerName, headerValue).build());
        }

        ImageCache.manager.asFile().load(glideUrl != null ? glideUrl : url).apply((BaseRequestOptions) requestOptions).listener((RequestListener) new RequestListener<File>() {
            public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<File> target, final boolean isFirstResource) {
                if (callback != null) {
                    callback.onError(e);
                }
                return false;
            }

            public boolean onResourceReady(final File resource, final Object model, final Target<File> target, final DataSource dataSource, final boolean isFirstResource) {
                if (callback != null) {
                    callback.onSuccess(resource.getAbsolutePath());
                }
                return false;
            }
        }).submit();
    }

    public static void clear() {
        if (ImageCache.glide != null) {
            ImageCache.glide.clearMemory();
            ImageCache.executorService.execute(new Runnable() {
                @Override
                public void run() {
                    ImageCache.glide.clearDiskCache();
                }
            });
        }
    }

    public interface Callback {
        void onSuccess(final Object p0);

        void onError(final Object p0);
    }
}
