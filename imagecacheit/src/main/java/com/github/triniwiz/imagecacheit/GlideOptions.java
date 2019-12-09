// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.CheckResult;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;

public class GlideOptions extends RequestOptions implements Cloneable
{
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions centerCropTransform2;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions noTransformation4;
    private static GlideOptions noAnimation5;
    
    @CheckResult
    @NonNull
    public static GlideOptions sizeMultiplierOf(@FloatRange(from = 0.0, to = 1.0) final float value) {
        return new GlideOptions().sizeMultiplier(value);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions diskCacheStrategyOf(@NonNull final DiskCacheStrategy strategy) {
        return new GlideOptions().diskCacheStrategy(strategy);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions priorityOf(@NonNull final Priority priority) {
        return new GlideOptions().priority(priority);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions placeholderOf(@Nullable final Drawable drawable) {
        return new GlideOptions().placeholder(drawable);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions placeholderOf(@DrawableRes final int id) {
        return new GlideOptions().placeholder(id);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions errorOf(@Nullable final Drawable drawable) {
        return new GlideOptions().error(drawable);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions errorOf(@DrawableRes final int id) {
        return new GlideOptions().error(id);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions skipMemoryCacheOf(final boolean skipMemoryCache) {
        return new GlideOptions().skipMemoryCache(skipMemoryCache);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions overrideOf(final int width, final int height) {
        return new GlideOptions().override(width, height);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions overrideOf(final int size) {
        return new GlideOptions().override(size);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions signatureOf(@NonNull final Key key) {
        return new GlideOptions().signature(key);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions fitCenterTransform() {
        if (GlideOptions.fitCenterTransform0 == null) {
            GlideOptions.fitCenterTransform0 = new GlideOptions().fitCenter().autoClone();
        }
        return GlideOptions.fitCenterTransform0;
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions centerInsideTransform() {
        if (GlideOptions.centerInsideTransform1 == null) {
            GlideOptions.centerInsideTransform1 = new GlideOptions().centerInside().autoClone();
        }
        return GlideOptions.centerInsideTransform1;
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions centerCropTransform() {
        if (GlideOptions.centerCropTransform2 == null) {
            GlideOptions.centerCropTransform2 = new GlideOptions().centerCrop().autoClone();
        }
        return GlideOptions.centerCropTransform2;
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions circleCropTransform() {
        if (GlideOptions.circleCropTransform3 == null) {
            GlideOptions.circleCropTransform3 = new GlideOptions().circleCrop().autoClone();
        }
        return GlideOptions.circleCropTransform3;
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions bitmapTransform(@NonNull final Transformation<Bitmap> transformation) {
        return new GlideOptions().transform(transformation);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions noTransformation() {
        if (GlideOptions.noTransformation4 == null) {
            GlideOptions.noTransformation4 = new GlideOptions().dontTransform().autoClone();
        }
        return GlideOptions.noTransformation4;
    }
    
    @CheckResult
    @NonNull
    public static <T> GlideOptions option(@NonNull final Option<T> option, @NonNull final T t) {
        return new GlideOptions().set(option, t);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions decodeTypeOf(@NonNull final Class<?> clazz) {
        return new GlideOptions().decode(clazz);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions formatOf(@NonNull final DecodeFormat format) {
        return new GlideOptions().format(format);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions frameOf(@IntRange(from = 0L) final long value) {
        return new GlideOptions().frame(value);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions downsampleOf(@NonNull final DownsampleStrategy strategy) {
        return new GlideOptions().downsample(strategy);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions timeoutOf(@IntRange(from = 0L) final int value) {
        return new GlideOptions().timeout(value);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions encodeQualityOf(@IntRange(from = 0L, to = 100L) final int value) {
        return new GlideOptions().encodeQuality(value);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions encodeFormatOf(@NonNull final Bitmap.CompressFormat format) {
        return new GlideOptions().encodeFormat(format);
    }
    
    @CheckResult
    @NonNull
    public static GlideOptions noAnimation() {
        if (GlideOptions.noAnimation5 == null) {
            GlideOptions.noAnimation5 = new GlideOptions().dontAnimate().autoClone();
        }
        return GlideOptions.noAnimation5;
    }
    
    @NonNull
    @CheckResult
    public GlideOptions sizeMultiplier(@FloatRange(from = 0.0, to = 1.0) final float value) {
        return (GlideOptions)super.sizeMultiplier(value);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions useUnlimitedSourceGeneratorsPool(final boolean flag) {
        return (GlideOptions)super.useUnlimitedSourceGeneratorsPool(flag);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions useAnimationPool(final boolean flag) {
        return (GlideOptions)super.useAnimationPool(flag);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions onlyRetrieveFromCache(final boolean flag) {
        return (GlideOptions)super.onlyRetrieveFromCache(flag);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions diskCacheStrategy(@NonNull final DiskCacheStrategy strategy) {
        return (GlideOptions)super.diskCacheStrategy(strategy);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions priority(@NonNull final Priority priority) {
        return (GlideOptions)super.priority(priority);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions placeholder(@Nullable final Drawable drawable) {
        return (GlideOptions)super.placeholder(drawable);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions placeholder(@DrawableRes final int id) {
        return (GlideOptions)super.placeholder(id);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions fallback(@Nullable final Drawable drawable) {
        return (GlideOptions)super.fallback(drawable);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions fallback(@DrawableRes final int id) {
        return (GlideOptions)super.fallback(id);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions error(@Nullable final Drawable drawable) {
        return (GlideOptions)super.error(drawable);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions error(@DrawableRes final int id) {
        return (GlideOptions)super.error(id);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions theme(@Nullable final Resources.Theme theme) {
        return (GlideOptions)super.theme(theme);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions skipMemoryCache(final boolean skip) {
        return (GlideOptions)super.skipMemoryCache(skip);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions override(final int width, final int height) {
        return (GlideOptions)super.override(width, height);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions override(final int size) {
        return (GlideOptions)super.override(size);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions signature(@NonNull final Key key) {
        return (GlideOptions)super.signature(key);
    }
    
    @CheckResult
    public GlideOptions clone() {
        return (GlideOptions)super.clone();
    }
    
    @NonNull
    @CheckResult
    public <Y> GlideOptions set(@NonNull final Option<Y> option, @NonNull final Y y) {
        return (GlideOptions)super.set((Option)option, (Object)y);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions decode(@NonNull final Class<?> clazz) {
        return (GlideOptions)super.decode((Class)clazz);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions encodeFormat(@NonNull final Bitmap.CompressFormat format) {
        return (GlideOptions)super.encodeFormat(format);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions encodeQuality(@IntRange(from = 0L, to = 100L) final int value) {
        return (GlideOptions)super.encodeQuality(value);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions frame(@IntRange(from = 0L) final long value) {
        return (GlideOptions)super.frame(value);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions format(@NonNull final DecodeFormat format) {
        return (GlideOptions)super.format(format);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions disallowHardwareConfig() {
        return (GlideOptions)super.disallowHardwareConfig();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions downsample(@NonNull final DownsampleStrategy strategy) {
        return (GlideOptions)super.downsample(strategy);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions timeout(@IntRange(from = 0L) final int value) {
        return (GlideOptions)super.timeout(value);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions optionalCenterCrop() {
        return (GlideOptions)super.optionalCenterCrop();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions centerCrop() {
        return (GlideOptions)super.centerCrop();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions optionalFitCenter() {
        return (GlideOptions)super.optionalFitCenter();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions fitCenter() {
        return (GlideOptions)super.fitCenter();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions optionalCenterInside() {
        return (GlideOptions)super.optionalCenterInside();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions centerInside() {
        return (GlideOptions)super.centerInside();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions optionalCircleCrop() {
        return (GlideOptions)super.optionalCircleCrop();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions circleCrop() {
        return (GlideOptions)super.circleCrop();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions transform(@NonNull final Transformation<Bitmap> transformation) {
        return (GlideOptions)super.transform((Transformation)transformation);
    }
    
    @SafeVarargs
    @NonNull
    @CheckResult
    public final GlideOptions transform(@NonNull final Transformation<Bitmap>... transformations) {
        return (GlideOptions)super.transform((Transformation[])transformations);
    }
    
    @SafeVarargs
    @Deprecated
    @NonNull
    @CheckResult
    public final GlideOptions transforms(@NonNull final Transformation<Bitmap>... transformations) {
        return (GlideOptions)super.transforms((Transformation[])transformations);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions optionalTransform(@NonNull final Transformation<Bitmap> transformation) {
        return (GlideOptions)super.optionalTransform((Transformation)transformation);
    }
    
    @NonNull
    @CheckResult
    public <Y> GlideOptions optionalTransform(@NonNull final Class<Y> clazz, @NonNull final Transformation<Y> transformation) {
        return (GlideOptions)super.optionalTransform((Class)clazz, (Transformation)transformation);
    }
    
    @NonNull
    @CheckResult
    public <Y> GlideOptions transform(@NonNull final Class<Y> clazz, @NonNull final Transformation<Y> transformation) {
        return (GlideOptions)super.transform((Class)clazz, (Transformation)transformation);
    }
    
    @NonNull
    @CheckResult
    public GlideOptions dontTransform() {
        return (GlideOptions)super.dontTransform();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions dontAnimate() {
        return (GlideOptions)super.dontAnimate();
    }
    
    @NonNull
    @CheckResult
    public GlideOptions apply(@NonNull final BaseRequestOptions<?> options) {
        return (GlideOptions)super.apply((BaseRequestOptions)options);
    }
    
    @NonNull
    public GlideOptions lock() {
        return (GlideOptions)super.lock();
    }
    
    @NonNull
    public GlideOptions autoClone() {
        return (GlideOptions)super.autoClone();
    }
}
