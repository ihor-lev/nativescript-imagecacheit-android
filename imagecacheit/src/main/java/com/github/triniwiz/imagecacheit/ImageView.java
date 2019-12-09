// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import java.util.concurrent.Executors;
import android.graphics.RectF;
import android.graphics.BitmapFactory;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.util.ArrayList;
import android.os.Handler;
import android.os.Looper;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSaturationFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageHueFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageOpacityFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageSepiaToneFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageColorInvertFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageGrayscaleFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.filter.GPUImageContrastFilter;
import jp.wasabeef.glide.transformations.internal.FastBlur;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import android.view.View;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Shader;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import android.content.res.Configuration;
import android.content.ComponentCallbacks;
import android.app.Application;
import java.io.File;
import android.graphics.Path;
import android.net.Uri;
import android.graphics.drawable.Drawable;
import android.content.res.TypedArray;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.content.Context;
import com.bumptech.glide.RequestManager;
import android.content.ComponentCallbacks2;
import java.util.concurrent.ExecutorService;
import android.graphics.Matrix;
import android.graphics.BitmapShader;
import android.graphics.Bitmap;
import android.graphics.Paint;
import com.bumptech.glide.RequestBuilder;
import android.annotation.SuppressLint;

@SuppressLint({ "AppCompatCustomView" })
public class ImageView extends android.widget.ImageView
{
    private Object src;
    private Object placeHolder;
    private RequestBuilder errorHolder;
    private Object fallbackImage;
    private int borderTopColor;
    private int borderRightColor;
    private int borderBottomColor;
    private int borderLeftColor;
    private int borderTopWidth;
    private int borderRightWidth;
    private int borderBottomWidth;
    private int borderLeftWidth;
    private int borderTopLeftRadius;
    private int borderTopRightRadius;
    private int borderBottomRightRadius;
    private int borderBottomLeftRadius;
    public final String TAG = "ImageView";
    Paint paint;
    Paint borderPaint;
    Bitmap bm;
    BitmapShader bitmapShader;
    Matrix shaderMatrix;
    private static ExecutorService executor;
    private static ComponentCallbacks2 componentCallbacks2;
    RequestManager requestManager;
    private String mFilter;
    
    public ImageView(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        this.paint = new Paint(1);
        this.borderPaint = new Paint(1);
        this.shaderMatrix = new Matrix();
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageView);
            try {
                final int topLeftRadius = a.getDimensionPixelSize(R.styleable.ImageView_borderTopLeftRadius, 0);
                this.setBorderTopLeftRadius(topLeftRadius);
                final int topRightRadius = a.getDimensionPixelSize(R.styleable.ImageView_borderTopRightRadius, 0);
                this.setBorderTopRightRadius(topRightRadius);
                final int bottomRightRadius = a.getDimensionPixelSize(R.styleable.ImageView_borderBottomRightRadius, 0);
                this.setBorderBottomRightRadius(bottomRightRadius);
                final int bottomLeftRadius = a.getDimensionPixelSize(R.styleable.ImageView_borderBottomLeftRadius, 0);
                this.setBorderBottomLeftRadius(bottomLeftRadius);
                final int radius = a.getDimensionPixelSize(R.styleable.ImageView_borderRadius, 0);
                if (radius > 0) {
                    this.setBorderRadius(radius);
                }
                final int borderTopColor = a.getColor(R.styleable.ImageView_borderTopColor, -1);
                if (a.hasValue(R.styleable.ImageView_borderTopColor)) {
                    this.setBorderTopColor(borderTopColor);
                }
                final int borderRightColor = a.getColor(R.styleable.ImageView_borderRightColor, -1);
                if (a.hasValue(R.styleable.ImageView_borderRightColor)) {
                    this.setBorderRightColor(borderRightColor);
                }
                final int borderBottomColor = a.getColor(R.styleable.ImageView_borderBottomColor, -1);
                if (a.hasValue(R.styleable.ImageView_borderBottomColor)) {
                    this.setBorderBottomColor(borderBottomColor);
                }
                final int borderLeftColor = a.getColor(R.styleable.ImageView_borderLeftColor, -1);
                if (a.hasValue(R.styleable.ImageView_borderLeftColor)) {
                    this.setBorderLeftColor(borderLeftColor);
                }
                final int borderColor = a.getColor(R.styleable.ImageView_borderColor, -1);
                if (a.hasValue(R.styleable.ImageView_borderColor)) {
                    this.setBorderColor(borderColor);
                }
                final int borderTopWidth = a.getDimensionPixelSize(R.styleable.ImageView_borderTopWidth, 0);
                if (borderTopWidth > 0) {
                    this.setBorderTopWidth(borderTopWidth);
                }
                final int borderRightWidth = a.getDimensionPixelSize(R.styleable.ImageView_borderRightWidth, 0);
                if (borderRightWidth > 0) {
                    this.setBorderRightWidth(borderRightWidth);
                }
                final int borderBottomWidth = a.getDimensionPixelSize(R.styleable.ImageView_borderBottomWidth, 0);
                if (borderBottomWidth > 0) {
                    this.setBorderBottomWidth(borderBottomWidth);
                }
                final int borderLeftWidth = a.getDimensionPixelSize(R.styleable.ImageView_borderLeftWidth, 0);
                if (borderLeftWidth > 0) {
                    this.setBorderLeftWidth(borderLeftWidth);
                }
                final int borderWidth = a.getDimensionPixelSize(R.styleable.ImageView_borderWidth, 0);
                if (borderWidth > 0) {
                    this.setBorderWidth(borderWidth);
                }
                this.mFilter = a.getString(R.styleable.ImageView_filter);
            }
            finally {
                a.recycle();
            }
        }
    }
    
    public void setFallbackImage(final Drawable drawable) {
        this.fallbackImage = drawable;
    }
    
    public void setFallbackImage(final Bitmap bitmap) {
        this.fallbackImage = bitmap;
    }
    
    public void setFallbackImage(final Uri uri) {
        Object fallback = uri;
        if (String.valueOf(fallback).startsWith("res://")) {
            fallback = this.getResources().getIdentifier(String.valueOf(fallback).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
        this.fallbackImage = fallback;
    }
    
    public void setFallbackImage(final Path path) {
        this.fallbackImage = path;
    }
    
    public void setFallbackImage(final int id) {
        this.fallbackImage = id;
    }
    
    public void setFallbackImage(final File file) {
        this.fallbackImage = file;
    }
    
    public void setFallbackImage(final String path) {
        if (String.valueOf(path).startsWith("res://")) {
            this.fallbackImage = this.getResources().getIdentifier(String.valueOf(path).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
        else {
            this.fallbackImage = path;
        }
    }
    
    public static void enableAutoMM(final Application application) {
        if (application == null) {
            return;
        }
        if (ImageView.componentCallbacks2 != null) {
            application.unregisterComponentCallbacks((ComponentCallbacks)ImageView.componentCallbacks2);
            ImageView.componentCallbacks2 = null;
        }
        application.registerComponentCallbacks((ComponentCallbacks)(ImageView.componentCallbacks2 = (ComponentCallbacks2)new ComponentCallbacks2() {
            public void onConfigurationChanged(@NonNull final Configuration newConfig) {
            }
            
            public void onLowMemory() {
                Glide.get(application.getApplicationContext()).clearMemory();
            }
            
            public void onTrimMemory(final int level) {
                Glide.get(application.getApplicationContext()).trimMemory(level);
            }
        }));
    }
    
    public static void disableAutoMM(final Application application) {
        if (application == null) {
            return;
        }
        if (ImageView.componentCallbacks2 != null) {
            application.unregisterComponentCallbacks((ComponentCallbacks)ImageView.componentCallbacks2);
            ImageView.componentCallbacks2 = null;
        }
    }
    
    public static void clear(final Context context, final boolean memoryOnly) {
        final Glide glide = Glide.get(context);
        glide.clearMemory();
        if (!memoryOnly) {
            ImageView.executor.execute(new Runnable() {
                @Override
                public void run() {
                    glide.clearDiskCache();
                }
            });
        }
    }
    
    public static void trimMemory(final Context context, final int level) {
        Glide.get(context).trimMemory(level);
    }
    
    @SuppressLint({ "CheckResult" })
    private void setBorderRadius(final RequestBuilder builder) {
        if (this.hasUniformBorder()) {
            builder.transform((Transformation)new ColoredRoundedCornerBorders(this.borderTopLeftRadius, 0, ColoredRoundedCornerBorders.CornerType.ALL, this.borderTopColor, this.borderTopWidth, -1, -1));
        }
        else if (this.hasBorderColor()) {
            final ColoredRoundedCornerBorders[] borders = { new ColoredRoundedCornerBorders(this.borderTopRightRadius, 0, ColoredRoundedCornerBorders.CornerType.BORDER_TOP, this.borderTopColor, this.borderTopWidth, -1, -1), new ColoredRoundedCornerBorders(this.borderBottomRightRadius, 0, ColoredRoundedCornerBorders.CornerType.BORDER_RIGHT, this.borderRightColor, this.borderRightWidth, -1, -1), new ColoredRoundedCornerBorders(this.borderBottomRightRadius, 0, ColoredRoundedCornerBorders.CornerType.BORDER_BOTTOM, this.borderBottomColor, this.borderBottomWidth, -1, -1), new ColoredRoundedCornerBorders(this.borderBottomLeftRadius, 0, ColoredRoundedCornerBorders.CornerType.BORDER_LEFT, this.borderLeftColor, this.borderLeftWidth, -1, -1) };
            builder.transform((Transformation[])borders);
        }
        else {
            final ColoredRoundedCornerBorders[] borders = { new ColoredRoundedCornerBorders(this.borderTopRightRadius, 0, ColoredRoundedCornerBorders.CornerType.TOP_RIGHT, 0, 0, -1, -1), new ColoredRoundedCornerBorders(this.borderBottomRightRadius, 0, ColoredRoundedCornerBorders.CornerType.BOTTOM_RIGHT, 0, 0, -1, -1), new ColoredRoundedCornerBorders(this.borderBottomLeftRadius, 0, ColoredRoundedCornerBorders.CornerType.BOTTOM_LEFT, 0, 0, -1, -1), new ColoredRoundedCornerBorders(this.borderTopLeftRadius, 0, ColoredRoundedCornerBorders.CornerType.TOP_LEFT, 0, 0, -1, -1) };
            builder.transform((Transformation[])borders);
        }
    }
    
    public void setBorderWidth(final int width) {
        this.borderTopWidth = width;
        this.borderRightWidth = width;
        this.borderBottomWidth = width;
        this.borderLeftWidth = width;
        this.invalidate();
    }
    
    public void setBorderColor(final int color) {
        this.borderTopColor = color;
        this.borderRightColor = color;
        this.borderBottomColor = color;
        this.borderLeftColor = color;
        this.invalidate();
    }
    
    public void setBorderRadius(final int radius) {
        this.borderTopLeftRadius = radius;
        this.borderTopRightRadius = radius;
        this.borderBottomRightRadius = radius;
        this.borderBottomLeftRadius = radius;
        this.invalidate();
    }
    
    public void setListener() {
    }
    
    protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        final Drawable drawable = this.getDrawable();
        if (drawable != null) {
            this.setImageDrawable(drawable);
            try {
                final Bitmap bitmapToScale = this.getBitmapFromDrawable(drawable);
                this.bm = Bitmap.createScaledBitmap(bitmapToScale, this.getWidth(), this.getHeight(), false);
                this.bitmapShader = new BitmapShader(this.bm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.paint.setShader((Shader)this.bitmapShader);
            }
            catch (OutOfMemoryError outOfMemoryError) {
                outOfMemoryError.printStackTrace();
            }
        }
    }
    
    private void updateBitmapSize() {
    }
    
    private Bitmap getBitmapFromDrawable(final Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }
        final BitmapPool pool = Glide.get(this.getContext()).getBitmapPool();
        final Bitmap bitmap = pool.get(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
    
    public void setImageBitmap(final Bitmap bm) {
        try {
            super.setImageBitmap(bm);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }
        final Drawable drawable = this.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = null;
            if (this.getWidth() > 0 && this.getHeight() > 0) {
                final Bitmap bitmapToScale = this.getBitmapFromDrawable(drawable);
                try {
                    bitmap = Bitmap.createScaledBitmap(bitmapToScale, this.getWidth(), this.getHeight(), false);
                }
                catch (OutOfMemoryError outOfMemoryError2) {
                    outOfMemoryError2.printStackTrace();
                }
            }
            else {
                bitmap = this.getBitmapFromDrawable(drawable);
            }
            if (bitmap != null) {
                this.bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.paint.setShader((Shader)this.bitmapShader);
            }
        }
        else {
            this.bitmapShader = null;
            this.paint.setShader((Shader)null);
        }
    }
    
    public void setImageDrawable(@Nullable final Drawable drawable) {
        try {
            super.setImageDrawable(drawable);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }
        if (drawable != null) {
            Bitmap bm = null;
            if (this.getWidth() > 0 && this.getHeight() > 0) {
                try {
                    final Bitmap bitmapToScale = this.getBitmapFromDrawable(drawable);
                    bm = Bitmap.createScaledBitmap(bitmapToScale, this.getWidth(), this.getHeight(), false);
                }
                catch (OutOfMemoryError error) {
                    error.printStackTrace();
                }
            }
            else {
                bm = this.getBitmapFromDrawable(drawable);
            }
            if (bm != null) {
                this.bitmapShader = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.paint.setShader((Shader)this.bitmapShader);
            }
        }
        else {
            this.bitmapShader = null;
            this.paint.setShader((Shader)null);
        }
    }
    
    public void setImageURI(@Nullable final Uri uri) {
        try {
            super.setImageURI(uri);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            outOfMemoryError.printStackTrace();
        }
        final Drawable drawable = this.getDrawable();
        if (drawable != null) {
            Bitmap bm = null;
            if (this.getWidth() > 0 && this.getHeight() > 0) {
                try {
                    final Bitmap bitmapToScale = this.getBitmapFromDrawable(drawable);
                    bm = Bitmap.createScaledBitmap(bitmapToScale, this.getWidth(), this.getHeight(), false);
                }
                catch (OutOfMemoryError error) {
                    error.printStackTrace();
                }
            }
            else {
                bm = this.getBitmapFromDrawable(drawable);
            }
            if (bm != null) {
                this.bitmapShader = new BitmapShader(bm, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.paint.setShader((Shader)this.bitmapShader);
            }
        }
        else {
            this.bitmapShader = null;
            this.paint.setShader((Shader)null);
        }
    }
    
    protected void onDraw(final Canvas canvas) {
        if (this.getDrawable() != null && (this.hasUniformBorder() || this.hasBorderColor())) {
            this.getDrawableWithBorder(canvas);
        }
        else {
            super.onDraw(canvas);
        }
    }
    
    @SuppressLint({ "CheckResult" })
    private void updateSrc(@Nullable final Object source) {
        if (this.requestManager != null) {
            this.requestManager.clear((View)this);
        }
        this.requestManager = Glide.with((View)this);
        final RequestBuilder requestBuilder = this.requestManager.load(source).addListener((RequestListener)new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<Drawable> target, final boolean isFirstResource) {
                return false;
            }
            
            public boolean onResourceReady(final Drawable resource, final Object model, final Target<Drawable> target, final DataSource dataSource, final boolean isFirstResource) {
                if (resource instanceof GifDrawable) {
                    return false;
                }
                if (ImageView.this.mFilter == null || ImageView.this.mFilter.isEmpty() || ImageView.this.mFilter.split(" ").length == 0) {
                    ImageView.this.setImageDrawable(resource);
                }
                else {
                    ImageView.executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            final GPUImage gpuImage = new GPUImage(ImageView.this.getContext());
                            if (ImageView.this.mFilter != null) {
                                final String[] split;
                                final String[] filters = split = ImageView.this.mFilter.split(" ");
                                for (final String filter : split) {
                                    final String value = ImageView.this.getValue(filter);
                                    if (filter.contains("blur")) {
                                        int width = -1;
                                        if (value.contains("%")) {
                                            width = ImageView.this.getWidth() * Integer.parseInt(value.replace("%", ""));
                                        }
                                        else if (value.contains("px")) {
                                            width = Integer.parseInt(value.replace("px", ""));
                                        }
                                        else if (value.contains("dip")) {
                                            width = Integer.parseInt(value.replace("dip", "")) * (int)ImageView.this.getContext().getResources().getDisplayMetrics().density;
                                        }
                                        if (width > -1) {
                                            gpuImage.setImage(FastBlur.blur(((BitmapDrawable)resource).getBitmap(), width, true));
                                        }
                                    }
                                    else if (filter.contains("contrast")) {
                                        if (value.contains("%")) {
                                            final float contrast = Float.parseFloat(value.replace("%", "")) / 100.0f;
                                            gpuImage.setFilter((GPUImageFilter)new GPUImageContrastFilter(contrast));
                                        }
                                    }
                                    else if (filter.contains("brightness")) {
                                        if (value.contains("%")) {
                                            float brightness = Float.parseFloat(value.replace("%", "")) / 100.0f;
                                            if (brightness >= 0.0f && brightness < 1.0f) {
                                                --brightness;
                                            }
                                            gpuImage.setFilter((GPUImageFilter)new GPUImageBrightnessFilter(brightness));
                                        }
                                    }
                                    else if (filter.contains("grayscale") || filter.contains("greyscale")) {
                                        gpuImage.setFilter((GPUImageFilter)new GPUImageGrayscaleFilter());
                                    }
                                    else if (filter.contains("invert")) {
                                        gpuImage.setFilter((GPUImageFilter)new GPUImageColorInvertFilter());
                                    }
                                    else if (filter.contains("sepia")) {
                                        final float sepia = Integer.parseInt(value.replace("%", "")) / 100.0f;
                                        gpuImage.setFilter((GPUImageFilter)new GPUImageSepiaToneFilter(sepia));
                                    }
                                    else if (filter.contains("opacity")) {
                                        float opacity;
                                        if (value.contains("%")) {
                                            opacity = Integer.parseInt(value.replace("%", "")) / 100.0f;
                                        }
                                        else if (value.contains(".")) {
                                            opacity = Float.parseFloat(value);
                                        }
                                        else {
                                            opacity = (float)(Integer.parseInt(value) / 100);
                                        }
                                        gpuImage.setFilter((GPUImageFilter)new GPUImageOpacityFilter(opacity));
                                    }
                                    else if (filter.contains("hue")) {
                                        float hue = 0.0f;
                                        if (value.contains("deg")) {
                                            hue = (float)Integer.parseInt(value.replace("deg", ""));
                                        }
                                        else if (value.contains("turn")) {
                                            hue = Float.parseFloat(value.replace("turn", "")) * 360.0f;
                                        }
                                        gpuImage.setFilter((GPUImageFilter)new GPUImageHueFilter(hue));
                                    }
                                    else if (filter.contains("saturate")) {
                                        float saturate = 1.0f;
                                        if (value.contains("%")) {
                                            saturate = Integer.parseInt(value.replace("%", "")) / 100.0f;
                                        }
                                        else if (value.contains(".")) {
                                            saturate = Float.parseFloat(value);
                                        }
                                        else {
                                            saturate = (float)Integer.parseInt(value);
                                        }
                                        gpuImage.setFilter((GPUImageFilter)new GPUImageSaturationFilter(saturate));
                                    }
                                }
                            }
                            final Handler handler = new Handler(Looper.getMainLooper());
                            try {
                                final Bitmap filteredImage = gpuImage.getBitmapWithFilterApplied(((BitmapDrawable)resource).getBitmap());
                                handler.post((Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        ImageView.this.setImageBitmap(filteredImage);
                                    }
                                });
                            }
                            catch (OutOfMemoryError outOfMemoryError) {
                                ImageView.clear(ImageView.this.getContext(), true);
                                try {
                                    final Bitmap filteredImage2 = gpuImage.getBitmapWithFilterApplied(((BitmapDrawable)resource).getBitmap());
                                    handler.post((Runnable)new Runnable() {
                                        @Override
                                        public void run() {
                                            ImageView.this.setImageBitmap(filteredImage2);
                                        }
                                    });
                                }
                                catch (OutOfMemoryError memoryError) {
                                    memoryError.printStackTrace();
                                }
                            }
                        }
                    });
                }
                return true;
            }
        });
        if (this.placeHolder != null) {
            if (this.placeHolder instanceof Integer) {
                requestBuilder.placeholder((int)this.placeHolder);
            }
            if (this.placeHolder instanceof Drawable) {
                requestBuilder.placeholder((Drawable)this.placeHolder);
            }
            if (this.placeHolder instanceof Bitmap) {
                requestBuilder.placeholder((Drawable)new BitmapDrawable(this.getResources(), (Bitmap)this.placeHolder));
            }
            if (this.placeHolder instanceof Uri) {
                requestBuilder.placeholder((Drawable)new BitmapDrawable(this.getResources(), ((Uri)this.placeHolder).toString()));
            }
            if (this.placeHolder instanceof String) {
                requestBuilder.placeholder(BitmapDrawable.createFromPath((String)this.placeHolder));
            }
            if (this.placeHolder instanceof File) {
                requestBuilder.placeholder(BitmapDrawable.createFromPath(((File)this.placeHolder).getPath()));
            }
        }
        if (this.fallbackImage != null) {
            if (this.fallbackImage instanceof Integer) {
                requestBuilder.fallback((int)this.fallbackImage);
            }
            if (this.fallbackImage instanceof Drawable) {
                requestBuilder.fallback((Drawable)this.fallbackImage);
            }
            if (this.fallbackImage instanceof Bitmap) {
                requestBuilder.fallback((Drawable)new BitmapDrawable(this.getResources(), (Bitmap)this.fallbackImage));
            }
            if (this.fallbackImage instanceof Uri) {
                requestBuilder.fallback((Drawable)new BitmapDrawable(this.getResources(), ((Uri)this.fallbackImage).toString()));
            }
            if (this.fallbackImage instanceof String) {
                requestBuilder.fallback(BitmapDrawable.createFromPath((String)this.fallbackImage));
            }
            if (this.fallbackImage instanceof File) {
                requestBuilder.fallback(BitmapDrawable.createFromPath(((File)this.fallbackImage).getPath()));
            }
        }
        final ArrayList<BitmapTransformation> bitmapTransformations = new ArrayList<BitmapTransformation>();
        switch (this.getScaleType()) {
            case FIT_CENTER: {
                bitmapTransformations.add((BitmapTransformation)new FitCenter());
                break;
            }
            case CENTER_CROP: {
                bitmapTransformations.add((BitmapTransformation)new CenterCrop());
                break;
            }
            case CENTER_INSIDE: {
                bitmapTransformations.add((BitmapTransformation)new CenterInside());
                break;
            }
        }
        if (!this.hasUniformBorder() || !this.hasBorderColor()) {
            bitmapTransformations.add((BitmapTransformation)new GranularRoundedCorners(this.getBorderTopLeftRadius(), this.getBorderTopRightRadius(), this.getBorderBottomRightRadius(), this.getBorderBottomLeftRadius()));
        }
        if (!bitmapTransformations.isEmpty()) {
            requestBuilder.transform((Transformation[])bitmapTransformations.toArray((Transformation[])new BitmapTransformation[0]));
            if (this.errorHolder != null) {
                this.errorHolder.transform((Transformation[])bitmapTransformations.toArray((Transformation[])new BitmapTransformation[0]));
            }
        }
        if (this.errorHolder != null) {
            requestBuilder.error(this.errorHolder);
        }
        requestBuilder.into((android.widget.ImageView)this);
    }
    
    public void setBitmapSrc(@Nullable final Bitmap bm) {
        this.updateSrc(bm);
    }
    
    public void setIdSrc(final int id) {
        this.updateSrc(id);
    }
    
    public void setDrawableSrc(@Nullable final Drawable drawable) {
        this.updateSrc(drawable);
    }
    
    public void setFileSrc(@Nullable final File file) {
        this.updateSrc(file);
    }
    
    public void setUriSrc(@Nullable final Uri uri) {
        Object source = uri;
        if (uri != null && String.valueOf(uri).startsWith("res://")) {
            source = this.getResources().getIdentifier(String.valueOf(uri).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
        this.updateSrc(source);
    }
    
    private static int calculateInSampleSize(final BitmapFactory.Options options, final int reqWidth, final int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            for (int halfHeight = height / 2, halfWidth = width / 2; halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth; inSampleSize *= 2) {}
        }
        return inSampleSize;
    }
    
    private static int getResourceId(final Context context, @Nullable final String res) {
        if (res != null && res.startsWith("res://")) {
            return context.getResources().getIdentifier(res.replace("res://", ""), "drawable", context.getPackageName());
        }
        return 0;
    }
    
    private void getDrawableWithBorder(final Canvas canvas) {
        if (this.hasUniformBorder()) {
            final int borderWidth = this.borderBottomWidth;
            final int radius = this.borderBottomLeftRadius;
            final Path path = new Path();
            path.addRoundRect(new RectF((float)borderWidth, (float)borderWidth, (float)(this.getWidth() - borderWidth), (float)(this.getHeight() - borderWidth)), (float)radius, (float)radius, Path.Direction.CW);
            if (this.hasBorderWidth()) {
                if (this.borderRightColor != 0) {
                    this.borderPaint.setColor(this.borderRightColor);
                }
                this.borderPaint.setStrokeWidth((float)borderWidth);
            }
            if (this.getUniformBorderWidth() == 0.0f && this.getUniformBorderColor() == 0) {
                this.borderPaint.setColor(0);
            }
            if (this.paint.getShader() != null) {
                canvas.drawRoundRect(new RectF((float)borderWidth, (float)borderWidth, (float)(this.getWidth() - borderWidth), (float)(this.getHeight() - borderWidth)), (float)radius, (float)radius, this.paint);
            }
            canvas.drawPath(path, this.borderPaint);
        }
        else if (this.hasBorderColor()) {
            final int right = this.getWidth();
            final int bottom = this.getHeight();
            final int margin = 0;
            final Path path2 = new Path();
            final Paint borderPaint = new Paint();
            borderPaint.setStyle(Paint.Style.STROKE);
            if (this.borderTopColor != 0) {
                borderPaint.setColor(this.borderTopColor);
            }
            borderPaint.setStrokeWidth((float)this.borderTopWidth);
            path2.moveTo((float)margin, 0.0f);
            path2.lineTo((float)right, 0.0f);
            path2.close();
            final RectF bounds = new RectF();
            if (this.borderTopWidth > 0) {
                bounds.set((float)margin, (float)margin, (float)right, (float)(bottom - this.borderTopWidth));
                canvas.drawPath(path2, borderPaint);
            }
            path2.reset();
            borderPaint.reset();
            borderPaint.setStyle(Paint.Style.STROKE);
            if (this.borderRightColor != 0) {
                borderPaint.setColor(this.borderRightColor);
            }
            borderPaint.setStrokeWidth((float)this.borderRightWidth);
            path2.moveTo((float)right, (float)margin);
            path2.lineTo((float)right, (float)bottom);
            path2.close();
            if (this.borderRightWidth > 0) {
                canvas.drawPath(path2, borderPaint);
            }
            path2.reset();
            borderPaint.reset();
            borderPaint.setStyle(Paint.Style.STROKE);
            if (this.borderBottomColor != 0) {
                borderPaint.setColor(this.borderBottomColor);
            }
            borderPaint.setStrokeWidth((float)this.borderBottomWidth);
            path2.moveTo((float)right, (float)bottom);
            path2.lineTo(0.0f, (float)bottom);
            path2.close();
            if (this.borderBottomWidth > 0) {
                canvas.drawPath(path2, borderPaint);
            }
            path2.reset();
            borderPaint.reset();
            borderPaint.setStyle(Paint.Style.STROKE);
            if (this.borderLeftColor != 0) {
                borderPaint.setColor(this.borderLeftColor);
            }
            borderPaint.setStrokeWidth((float)this.borderLeftWidth);
            path2.moveTo(0.0f, (float)bottom);
            path2.lineTo(0.0f, 0.0f);
            path2.close();
            if (this.paint.getShader() != null) {
                canvas.drawRect(new RectF((float)this.borderLeftWidth, (float)this.borderTopWidth, (float)(right - this.borderRightWidth), (float)(bottom - this.borderBottomWidth)), this.paint);
            }
            if (this.borderLeftWidth > 0) {
                canvas.drawPath(path2, borderPaint);
            }
        }
    }
    
    private BitmapDrawable getDrawableWithBorder(final Object source) {
        if (source == null) {
            return null;
        }
        Bitmap image = null;
        final int width = this.getWidth();
        final int height = this.getHeight();
        final BitmapFactory.Options opts = new BitmapFactory.Options();
        if (source instanceof String) {
            if (((String)source).startsWith("res://")) {
                final int id = getResourceId(this.getContext(), (String)source);
                if (id > 0) {
                    opts.inJustDecodeBounds = true;
                    BitmapFactory.decodeResource(this.getResources(), id, opts);
                    final int srcWidth = opts.outWidth;
                    final int srcHeight = opts.outHeight;
                    opts.inJustDecodeBounds = false;
                    opts.inScaled = true;
                    opts.inDensity = srcWidth;
                    opts.inTargetDensity = width;
                    opts.inSampleSize = calculateInSampleSize(opts, width, height);
                    opts.inTargetDensity = width * opts.inSampleSize;
                    image = BitmapFactory.decodeResource(this.getResources(), id, opts);
                }
            }
            else {
                Object path = source;
                if (!((String)source).startsWith("~/")) {
                    if (((String)source).startsWith("file://")) {
                        path = ((String)source).replace("file://", "");
                    }
                }
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(String.valueOf(path), opts);
                final int srcWidth = opts.outWidth;
                final int srcHeight = opts.outHeight;
                opts.inJustDecodeBounds = false;
                opts.inScaled = true;
                opts.inDensity = srcWidth;
                opts.inSampleSize = calculateInSampleSize(opts, width, height);
                opts.inTargetDensity = width * opts.inSampleSize;
                image = BitmapFactory.decodeFile(String.valueOf(path), opts);
            }
        }
        else if (source instanceof Integer) {
            if ((int)source > 0) {
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(this.getResources(), (int)source, opts);
                final int srcWidth = opts.outWidth;
                final int srcHeight = opts.outHeight;
                opts.inJustDecodeBounds = false;
                opts.inScaled = true;
                opts.inDensity = srcWidth;
                opts.inSampleSize = calculateInSampleSize(opts, width, height);
                opts.inTargetDensity = width * opts.inSampleSize;
                image = BitmapFactory.decodeResource(this.getResources(), (int)source, opts);
            }
        }
        else if (source instanceof Bitmap) {
            image = (Bitmap)source;
            final int srcWidth = image.getWidth();
            final int srcHeight = image.getHeight();
        }
        else if (source instanceof BitmapDrawable) {
            image = ((BitmapDrawable)this.placeHolder).getBitmap();
            final int srcWidth = image.getWidth();
            image.getHeight();
        }
        if (image != null) {
            final BitmapPool pool = Glide.get(this.getContext()).getBitmapPool();
            final Bitmap bitmap = pool.get(width, height, opts.inPreferredConfig);
            final Canvas canvas = new Canvas(bitmap);
            final Paint paint = new Paint();
            paint.setAntiAlias(true);
            if (image.getWidth() != width || image.getHeight() != height) {
                image = Bitmap.createScaledBitmap(image, width, height, true);
            }
            BitmapShader shader = new BitmapShader(image, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            paint.setShader((Shader)shader);
            if (this.hasUniformBorder()) {
                final int borderWidth = this.borderBottomWidth;
                final int radius = this.borderBottomLeftRadius;
                final Path path2 = new Path();
                path2.addRoundRect(new RectF((float)borderWidth, (float)borderWidth, (float)(width - borderWidth), (float)(height - borderWidth)), (float)radius, (float)radius, Path.Direction.CW);
                final Paint borderPaint = new Paint();
                borderPaint.setAntiAlias(true);
                borderPaint.setStyle(Paint.Style.STROKE);
                if (this.hasBorderWidth()) {
                    if (this.borderRightColor != 0) {
                        borderPaint.setColor(this.borderRightColor);
                    }
                    borderPaint.setStrokeWidth((float)borderWidth);
                    canvas.drawRoundRect(new RectF((float)borderWidth, (float)borderWidth, (float)(width - borderWidth), (float)(height - borderWidth)), (float)radius, (float)radius, paint);
                }
                canvas.drawPath(path2, borderPaint);
            }
            else if (this.hasBorderColor()) {
                final int right = width;
                final int bottom = height;
                final int margin = 0;
                final Path path3 = new Path();
                final Paint borderPaint2 = new Paint();
                borderPaint2.setStyle(Paint.Style.STROKE);
                if (this.borderTopColor != 0) {
                    borderPaint2.setColor(this.borderTopColor);
                }
                borderPaint2.setStrokeWidth((float)this.borderTopWidth);
                path3.moveTo((float)margin, 0.0f);
                path3.lineTo((float)right, 0.0f);
                path3.close();
                if (this.borderTopWidth > 0) {
                    canvas.drawRect(new RectF((float)margin, (float)margin, (float)right, (float)bottom), paint);
                    canvas.drawPath(path3, borderPaint2);
                }
                shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                paint.setShader((Shader)shader);
                path3.reset();
                borderPaint2.reset();
                borderPaint2.setStyle(Paint.Style.STROKE);
                if (this.borderRightColor != 0) {
                    borderPaint2.setColor(this.borderRightColor);
                }
                borderPaint2.setStrokeWidth((float)this.borderRightWidth);
                path3.moveTo((float)right, (float)margin);
                path3.lineTo((float)right, (float)bottom);
                path3.close();
                if (this.borderRightWidth > 0) {
                    canvas.drawRect(new RectF((float)margin, (float)margin, (float)right, (float)bottom), paint);
                    canvas.drawPath(path3, borderPaint2);
                }
                path3.reset();
                borderPaint2.reset();
                borderPaint2.setStyle(Paint.Style.STROKE);
                if (this.borderBottomColor != 0) {
                    borderPaint2.setColor(this.borderBottomColor);
                }
                borderPaint2.setStrokeWidth((float)this.borderBottomWidth);
                path3.moveTo((float)right, (float)bottom);
                path3.lineTo(0.0f, (float)bottom);
                path3.close();
                if (this.borderBottomWidth > 0) {
                    canvas.drawRect(new RectF((float)margin, (float)margin, (float)right, (float)bottom), paint);
                    canvas.drawPath(path3, borderPaint2);
                }
                path3.reset();
                borderPaint2.reset();
                borderPaint2.setStyle(Paint.Style.STROKE);
                if (this.borderLeftColor != 0) {
                    borderPaint2.setColor(this.borderLeftColor);
                }
                borderPaint2.setStrokeWidth((float)this.borderLeftWidth);
                path3.moveTo(0.0f, (float)bottom);
                path3.lineTo(0.0f, 0.0f);
                path3.close();
                if (this.borderLeftWidth > 0) {
                    canvas.drawRect(new RectF(0.0f, 0.0f, (float)right, (float)bottom), paint);
                    canvas.drawPath(path3, borderPaint2);
                }
            }
            else {
                int radius2 = this.borderTopLeftRadius;
                int diameter = radius2 * 2;
                final int margin = 0;
                final int right2 = width;
                final int bottom2 = height;
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float)(margin + diameter), (float)(margin + diameter)), (float)radius2, (float)radius2, paint);
                canvas.drawRect(new RectF((float)margin, (float)(margin + radius2), (float)(margin + radius2), (float)bottom2), paint);
                canvas.drawRect(new RectF((float)(margin + radius2), (float)margin, (float)right2, (float)bottom2), paint);
                radius2 = this.borderTopRightRadius;
                diameter = radius2 * 2;
                canvas.drawRoundRect(new RectF((float)(right2 - diameter), (float)margin, (float)right2, (float)(margin + diameter)), (float)radius2, (float)radius2, paint);
                canvas.drawRect(new RectF((float)margin, (float)margin, (float)(right2 - radius2), (float)bottom2), paint);
                canvas.drawRect(new RectF((float)(right2 - radius2), (float)(margin + radius2), (float)right2, (float)bottom2), paint);
                radius2 = this.borderBottomRightRadius;
                diameter = radius2 * 2;
                canvas.drawRoundRect(new RectF((float)margin, (float)(bottom2 - diameter), (float)(margin + diameter), (float)bottom2), (float)radius2, (float)radius2, paint);
                canvas.drawRect(new RectF((float)margin, (float)margin, (float)(margin + diameter), (float)(bottom2 - radius2)), paint);
                canvas.drawRect(new RectF((float)(margin + radius2), (float)margin, (float)right2, (float)bottom2), paint);
                radius2 = this.borderBottomLeftRadius;
                diameter = radius2 * 2;
                canvas.drawRoundRect(new RectF((float)(right2 - diameter), (float)(bottom2 - diameter), (float)right2, (float)bottom2), (float)radius2, (float)radius2, paint);
                canvas.drawRect(new RectF((float)margin, (float)margin, (float)(right2 - radius2), (float)bottom2), paint);
                canvas.drawRect(new RectF((float)(right2 - radius2), (float)margin, (float)right2, (float)(bottom2 - radius2)), paint);
            }
            return new BitmapDrawable(this.getResources(), bitmap);
        }
        return null;
    }
    
    public boolean hasBorderColor() {
        return this.borderTopColor != 0 || (this.borderRightColor != 0 | this.borderBottomColor != 0) || this.borderLeftColor != 0;
    }
    
    public boolean hasBorderWidth() {
        return this.borderTopWidth != 0 || this.borderRightWidth != 0 || this.borderBottomWidth != 0 || this.borderLeftWidth != 0;
    }
    
    public boolean hasBorderRadius() {
        return this.borderTopLeftRadius != 0 || (this.borderTopRightRadius != 0 && this.borderBottomRightRadius != 0 && this.borderBottomLeftRadius != 0);
    }
    
    public boolean hasUniformBorderColor() {
        return this.borderTopColor == this.borderRightColor && this.borderTopColor == this.borderBottomColor && this.borderTopColor == this.borderLeftColor;
    }
    
    public boolean hasUniformBorderWidth() {
        return this.borderTopWidth == this.borderRightWidth && this.borderTopWidth == this.borderBottomWidth && this.borderTopWidth == this.borderLeftWidth;
    }
    
    public boolean hasUniformBorderRadius() {
        return this.borderTopLeftRadius == this.borderTopRightRadius && this.borderTopLeftRadius == this.borderBottomRightRadius && this.borderTopLeftRadius == this.borderBottomLeftRadius;
    }
    
    public boolean hasUniformBorder() {
        return this.hasUniformBorderColor() && this.hasUniformBorderWidth() && this.hasUniformBorderRadius();
    }
    
    private String getValue(final String value) {
        return value.substring(value.indexOf(40) + 1, value.indexOf(41));
    }
    
    public void setFilter(final String filter) {
        this.mFilter = filter;
    }
    
    public void setPlaceHolder(final Drawable drawable) {
        this.placeHolder = drawable;
    }
    
    public void setPlaceHolder(final Bitmap bitmap) {
        this.placeHolder = bitmap;
    }
    
    public void setPlaceHolder(final Uri uri) {
        this.placeHolder = uri;
        if (String.valueOf(this.placeHolder).startsWith("res://")) {
            this.placeHolder = this.getResources().getIdentifier(String.valueOf(this.placeHolder).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
    }
    
    public void setPlaceHolder(final File file) {
        this.placeHolder = file;
    }
    
    public void setPlaceHolder(final String path) {
        if (String.valueOf(path).startsWith("res://")) {
            this.placeHolder = this.getResources().getIdentifier(String.valueOf(path).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
        else {
            this.placeHolder = path;
        }
    }
    
    public void setPlaceHolder(final int id) {
        this.placeHolder = id;
    }
    
    public void setErrorHolder(final Drawable drawable) {
        this.errorHolder = Glide.with((View)this).load(drawable);
    }
    
    public void setErrorHolder(final Bitmap bitmap) {
        this.errorHolder = Glide.with((View)this).load(bitmap);
    }
    
    public void setErrorHolder(final Uri uri) {
        Object error = uri;
        if (String.valueOf(error).startsWith("res://")) {
            error = this.getResources().getIdentifier(String.valueOf(error).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
        this.errorHolder = Glide.with((View)this).load(error);
    }
    
    public void setErrorHolder(final Path path) {
        this.errorHolder = Glide.with((View)this).load((Object)path);
    }
    
    public void setErrorHolder(final int id) {
        this.errorHolder = Glide.with((View)this).load(Integer.valueOf(id));
    }
    
    public void setErrorHolder(final File file) {
        this.errorHolder = Glide.with((View)this).load(file);
    }
    
    public void setErrorHolder(final String path) {
        Object error = path;
        if (String.valueOf(path).startsWith("res://")) {
            error = this.getResources().getIdentifier(String.valueOf(path).replace("res://", ""), "drawable", this.getContext().getPackageName());
        }
        this.errorHolder = Glide.with((View)this).load(error);
    }
    
    public void setBorderTopWidth(final int borderTopWidth) {
        this.borderTopWidth = borderTopWidth;
        this.invalidate();
    }
    
    public void setBorderRightWidth(final int borderRightWidth) {
        this.borderRightWidth = borderRightWidth;
        this.invalidate();
    }
    
    public void setBorderBottomWidth(final int borderBottomWidth) {
        this.borderBottomWidth = borderBottomWidth;
        this.invalidate();
    }
    
    public void setBorderLeftWidth(final int borderLeftWidth) {
        this.borderLeftWidth = borderLeftWidth;
        this.invalidate();
    }
    
    public void setBorderTopLeftRadius(final int borderTopLeftRadius) {
        this.borderTopLeftRadius = borderTopLeftRadius;
        this.invalidate();
    }
    
    public void setBorderTopRightRadius(final int borderTopRightRadius) {
        this.borderTopRightRadius = borderTopRightRadius;
        this.invalidate();
    }
    
    public void setBorderBottomRightRadius(final int borderBottomRightRadius) {
        this.borderBottomRightRadius = borderBottomRightRadius;
        this.invalidate();
    }
    
    public void setBorderBottomLeftRadius(final int borderBottomLeftRadius) {
        this.borderBottomLeftRadius = borderBottomLeftRadius;
        this.invalidate();
    }
    
    public void setBorderTopColor(final int borderTopColor) {
        this.borderTopColor = borderTopColor;
        this.invalidate();
    }
    
    public void setBorderRightColor(final int borderRightColor) {
        this.borderRightColor = borderRightColor;
        this.invalidate();
    }
    
    public void setBorderBottomColor(final int borderBottomColor) {
        this.borderBottomColor = borderBottomColor;
        this.invalidate();
    }
    
    public void setBorderLeftColor(final int borderLeftColor) {
        this.borderLeftColor = borderLeftColor;
        this.invalidate();
    }
    
    public int getBorderTopColor() {
        return this.borderTopColor;
    }
    
    public int getBorderRightColor() {
        return this.borderRightColor;
    }
    
    public int getBorderBottomColor() {
        return this.borderBottomColor;
    }
    
    public int getBorderLeftColor() {
        return this.borderLeftColor;
    }
    
    public int getUniformBorderColor() {
        if (this.hasUniformBorderColor()) {
            return this.borderTopColor;
        }
        return 0;
    }
    
    public float getBorderTopWidth() {
        return (float)this.borderTopWidth;
    }
    
    public float getBorderRightWidth() {
        return (float)this.borderRightWidth;
    }
    
    public float getBorderBottomWidth() {
        return (float)this.borderBottomWidth;
    }
    
    public float getBorderLeftWidth() {
        return (float)this.borderLeftWidth;
    }
    
    public float getUniformBorderWidth() {
        if (this.hasUniformBorderWidth()) {
            return (float)this.borderTopWidth;
        }
        return 0.0f;
    }
    
    public float getBorderTopLeftRadius() {
        return (float)this.borderTopLeftRadius;
    }
    
    public float getBorderTopRightRadius() {
        return (float)this.borderTopRightRadius;
    }
    
    public float getBorderBottomRightRadius() {
        return (float)this.borderBottomRightRadius;
    }
    
    public float getBorderBottomLeftRadius() {
        return (float)this.borderBottomLeftRadius;
    }
    
    public float getUniformBorderRadius() {
        if (this.hasUniformBorderRadius()) {
            return (float)this.borderTopLeftRadius;
        }
        return 0.0f;
    }
    
    static {
        ImageView.executor = Executors.newSingleThreadExecutor();
    }
}
