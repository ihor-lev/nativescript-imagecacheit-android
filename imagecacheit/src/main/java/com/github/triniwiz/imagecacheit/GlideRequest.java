// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RawRes;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;

import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    GlideRequest(@NonNull final Class<TranscodeType> transcodeClass, @NonNull final RequestBuilder<?> other) {
        super((Class) transcodeClass, (RequestBuilder) other);
    }

    GlideRequest(@NonNull final Glide glide, @NonNull final RequestManager requestManager, @NonNull final Class<TranscodeType> transcodeClass, @NonNull final Context context) {
        super(glide, requestManager, (Class) transcodeClass, context);
    }

    @CheckResult
    @NonNull
    protected GlideRequest<File> getDownloadOnlyRequest() {
        return new GlideRequest<File>(File.class, this).apply((BaseRequestOptions<?>) GlideRequest.DOWNLOAD_ONLY_OPTIONS);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> sizeMultiplier(@FloatRange(from = 0.0, to = 1.0) final float value) {
        return (GlideRequest) super.sizeMultiplier(value);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(final boolean flag) {
        return (GlideRequest) super.useUnlimitedSourceGeneratorsPool(flag);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> useAnimationPool(final boolean flag) {
        return (GlideRequest) super.useAnimationPool(flag);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> onlyRetrieveFromCache(final boolean flag) {
        return (GlideRequest) super.onlyRetrieveFromCache(flag);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> diskCacheStrategy(@NonNull final DiskCacheStrategy strategy) {
        return (GlideRequest) super.diskCacheStrategy(strategy);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> priority(@NonNull final Priority priority) {
        return (GlideRequest) super.priority(priority);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@Nullable final Drawable drawable) {
        return (GlideRequest) super.placeholder(drawable);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> placeholder(@DrawableRes final int id) {
        return (GlideRequest) super.placeholder(id);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@Nullable final Drawable drawable) {
        return (GlideRequest) super.fallback(drawable);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fallback(@DrawableRes final int id) {
        return (GlideRequest) super.fallback(id);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@Nullable final Drawable drawable) {
        return (GlideRequest) super.error(drawable);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> error(@DrawableRes final int id) {
        return (GlideRequest) super.error(id);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> theme(@Nullable final Resources.Theme theme) {
        return (GlideRequest) super.theme(theme);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> skipMemoryCache(final boolean skip) {
        return (GlideRequest) super.skipMemoryCache(skip);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(final int width, final int height) {
        return (GlideRequest) super.override(width, height);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> override(final int size) {
        return (GlideRequest) super.override(size);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> signature(@NonNull final Key key) {
        return (GlideRequest) super.signature(key);
    }

    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> set(@NonNull final Option<Y> option, @NonNull final Y y) {
        return (GlideRequest) super.set((Option) option, (Object) y);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> decode(@NonNull final Class<?> clazz) {
        return (GlideRequest) super.decode((Class) clazz);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeFormat(@NonNull final Bitmap.CompressFormat format) {
        return (GlideRequest) super.encodeFormat(format);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> encodeQuality(@IntRange(from = 0L, to = 100L) final int value) {
        return (GlideRequest) super.encodeQuality(value);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> frame(@IntRange(from = 0L) final long value) {
        return (GlideRequest) super.frame(value);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> format(@NonNull final DecodeFormat format) {
        return (GlideRequest) super.format(format);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        return (GlideRequest) super.disallowHardwareConfig();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> downsample(@NonNull final DownsampleStrategy strategy) {
        return (GlideRequest) super.downsample(strategy);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> timeout(@IntRange(from = 0L) final int value) {
        return (GlideRequest) super.timeout(value);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterCrop() {
        return (GlideRequest) super.optionalCenterCrop();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerCrop() {
        return (GlideRequest) super.centerCrop();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalFitCenter() {
        return (GlideRequest) super.optionalFitCenter();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> fitCenter() {
        return (GlideRequest) super.fitCenter();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCenterInside() {
        return (GlideRequest) super.optionalCenterInside();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> centerInside() {
        return (GlideRequest) super.centerInside();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalCircleCrop() {
        return (GlideRequest) super.optionalCircleCrop();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> circleCrop() {
        return (GlideRequest) super.circleCrop();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull final Transformation<Bitmap> transformation) {
        return (GlideRequest) super.transform((Transformation) transformation);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transform(@NonNull final Transformation<Bitmap>... transformations) {
        return (GlideRequest) super.transform((Transformation[]) transformations);
    }

    @Deprecated
    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transforms(@NonNull final Transformation<Bitmap>... transformations) {
        return (GlideRequest) super.transforms((Transformation[]) transformations);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> optionalTransform(@NonNull final Transformation<Bitmap> transformation) {
        return (GlideRequest) super.optionalTransform((Transformation) transformation);
    }

    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> optionalTransform(@NonNull final Class<Y> clazz, @NonNull final Transformation<Y> transformation) {
        return (GlideRequest) super.optionalTransform((Class) clazz, (Transformation) transformation);
    }

    @NonNull
    @CheckResult
    public <Y> GlideRequest<TranscodeType> transform(@NonNull final Class<Y> clazz, @NonNull final Transformation<Y> transformation) {
        return (GlideRequest) super.transform((Class) clazz, (Transformation) transformation);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontTransform() {
        return (GlideRequest) super.dontTransform();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> dontAnimate() {
        return (GlideRequest) super.dontAnimate();
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> apply(@NonNull final BaseRequestOptions<?> options) {
        return (GlideRequest) super.apply((BaseRequestOptions) options);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> transition(@NonNull final TransitionOptions<?, ? super TranscodeType> options) {
        return (GlideRequest) super.transition((TransitionOptions) options);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> listener(@Nullable final RequestListener<TranscodeType> listener) {
        return (GlideRequest) super.listener((RequestListener) listener);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> addListener(@Nullable final RequestListener<TranscodeType> listener) {
        return (GlideRequest) super.addListener((RequestListener) listener);
    }

    @NonNull
    public GlideRequest<TranscodeType> error(@Nullable final RequestBuilder<TranscodeType> builder) {
        return (GlideRequest) super.error((RequestBuilder) builder);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(@Nullable final RequestBuilder<TranscodeType> builder) {
        return (GlideRequest) super.thumbnail((RequestBuilder) builder);
    }

    @SafeVarargs
    @NonNull
    @CheckResult
    public final GlideRequest<TranscodeType> thumbnail(@Nullable final RequestBuilder<TranscodeType>... builders) {
        return (GlideRequest) super.thumbnail((RequestBuilder[]) builders);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> thumbnail(final float sizeMultiplier) {
        return (GlideRequest) super.thumbnail(sizeMultiplier);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final Object o) {
        return (GlideRequest) super.load(o);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final Bitmap bitmap) {
        return (GlideRequest) super.load(bitmap);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final Drawable drawable) {
        return (GlideRequest) super.load(drawable);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final String string) {
        return (GlideRequest) super.load(string);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final Uri uri) {
        return (GlideRequest) super.load(uri);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final File file) {
        return (GlideRequest) super.load(file);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@RawRes @DrawableRes @Nullable final Integer id) {
        return (GlideRequest) super.load(id);
    }

    @Deprecated
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final URL url) {
        return (GlideRequest) super.load(url);
    }

    @NonNull
    @CheckResult
    public GlideRequest<TranscodeType> load(@Nullable final byte[] bytes) {
        return (GlideRequest) super.load(bytes);
    }

    @CheckResult
    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest) super.clone();
    }
}
