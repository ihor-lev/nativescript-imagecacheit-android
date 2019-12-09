// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.net.URL;

public class GlideRequests extends RequestManager {
    public GlideRequests(@NonNull final Glide glide, @NonNull final Lifecycle lifecycle, @NonNull final RequestManagerTreeNode treeNode, @NonNull final Context context) {
        super(glide, lifecycle, treeNode, context);
    }

    @CheckResult
    @NonNull
    public <ResourceType> GlideRequest<ResourceType> as(@NonNull final Class<ResourceType> resourceClass) {
        return new GlideRequest<ResourceType>(this.glide, this, resourceClass, this.context);
    }

    @NonNull
    public synchronized GlideRequests applyDefaultRequestOptions(@NonNull final RequestOptions options) {
        return (GlideRequests) super.applyDefaultRequestOptions(options);
    }

    @NonNull
    public synchronized GlideRequests setDefaultRequestOptions(@NonNull final RequestOptions options) {
        return (GlideRequests) super.setDefaultRequestOptions(options);
    }

    @NonNull
    public GlideRequests addDefaultRequestListener(final RequestListener<Object> listener) {
        return (GlideRequests) super.addDefaultRequestListener((RequestListener) listener);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest<Bitmap>) super.asBitmap();
    }

    @NonNull
    @CheckResult
    public GlideRequest<GifDrawable> asGif() {
        return (GlideRequest<GifDrawable>) super.asGif();
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest<Drawable>) super.asDrawable();
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final Bitmap bitmap) {
        return (GlideRequest<Drawable>) super.load(bitmap);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final Drawable drawable) {
        return (GlideRequest<Drawable>) super.load(drawable);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final String string) {
        return (GlideRequest<Drawable>) super.load(string);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final Uri uri) {
        return (GlideRequest<Drawable>) super.load(uri);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final File file) {
        return (GlideRequest<Drawable>) super.load(file);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@RawRes @DrawableRes @Nullable final Integer id) {
        return (GlideRequest<Drawable>) super.load(id);
    }

    @Deprecated
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final URL url) {
        return (GlideRequest<Drawable>) super.load(url);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final byte[] bytes) {
        return (GlideRequest<Drawable>) super.load(bytes);
    }

    @NonNull
    @CheckResult
    public GlideRequest<Drawable> load(@Nullable final Object o) {
        return (GlideRequest<Drawable>) super.load(o);
    }

    @NonNull
    @CheckResult
    public GlideRequest<File> downloadOnly() {
        return (GlideRequest<File>) super.downloadOnly();
    }

    @NonNull
    @CheckResult
    public GlideRequest<File> download(@Nullable final Object o) {
        return (GlideRequest<File>) super.download(o);
    }

    @NonNull
    @CheckResult
    public GlideRequest<File> asFile() {
        return (GlideRequest<File>) super.asFile();
    }

    protected void setRequestOptions(@NonNull final RequestOptions toSet) {
        if (toSet instanceof GlideOptions) {
            super.setRequestOptions(toSet);
        } else {
            super.setRequestOptions((RequestOptions) new GlideOptions().apply((BaseRequestOptions<?>) toSet));
        }
    }
}
