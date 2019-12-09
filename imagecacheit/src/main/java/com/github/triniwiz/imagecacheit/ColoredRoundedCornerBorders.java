// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

import java.security.MessageDigest;

import jp.wasabeef.glide.transformations.BitmapTransformation;

public class ColoredRoundedCornerBorders extends BitmapTransformation {
    private static final int VERSION = 1;
    private static final String ID = "com.github.triniwiz.imagedemo.ColoredRoundedCornerBorders.1";
    private int radius;
    private int diameter;
    private int margin;
    private CornerType cornerType;
    private int cornerWidth;
    private int color;
    private int viewWidth;
    private int viewHeight;

    public ColoredRoundedCornerBorders(final int radius, final int margin) {
        this(radius, margin, CornerType.ALL, 0, 0, -1, -1);
    }

    public ColoredRoundedCornerBorders(final int radius, final int margin, final CornerType cornerType, final int color, final int cornerWidth, final int width, final int height) {
        this.radius = radius;
        this.diameter = this.radius * 2;
        this.margin = margin;
        this.cornerType = cornerType;
        this.color = color;
        this.cornerWidth = cornerWidth;
        this.viewWidth = width;
        this.viewHeight = height;
    }

    protected Bitmap transform(@NonNull final Context context, @NonNull final BitmapPool pool, @NonNull final Bitmap toTransform, final int outWidth, final int outHeight) {
        final int width = toTransform.getWidth();
        final int height = toTransform.getHeight();
        int currentHeight = outHeight;
        int currentWidth = outWidth;
        if (this.viewWidth > -1) {
            currentWidth = this.viewWidth;
        }
        if (this.viewHeight > -1) {
            currentHeight = this.viewHeight;
        }
        final Bitmap bitmap = pool.get(currentWidth, currentHeight, Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(true);
        final Canvas canvas = new Canvas(bitmap);
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader((Shader) new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        this.drawRoundRect(canvas, paint, (float) currentWidth, (float) currentHeight);
        return bitmap;
    }

    private void drawRoundRect(final Canvas canvas, final Paint paint, final float width, final float height) {
        final float right = width - this.margin;
        final float bottom = height - this.margin;
        switch (this.cornerType) {
            case ALL: {
                canvas.drawRoundRect(new RectF((float) (this.margin + this.cornerWidth), (float) (this.margin + this.cornerWidth), right - this.cornerWidth, bottom - this.cornerWidth), (float) this.radius, (float) this.radius, paint);
                final Path path = new Path();
                path.addRoundRect(new RectF((float) (this.margin + this.cornerWidth), (float) (this.margin + this.cornerWidth), right - this.cornerWidth, bottom - this.cornerWidth), (float) this.radius, (float) this.radius, Path.Direction.CW);
                final Paint borderPaint = new Paint();
                borderPaint.setAntiAlias(true);
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                if (this.cornerWidth > 0) {
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
            case TOP_LEFT: {
                this.drawTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            }
            case TOP_RIGHT: {
                this.drawTopRightRoundRect(canvas, paint, right, bottom);
                break;
            }
            case BOTTOM_LEFT: {
                this.drawBottomLeftRoundRect(canvas, paint, right, bottom);
                break;
            }
            case BOTTOM_RIGHT: {
                this.drawBottomRightRoundRect(canvas, paint, right, bottom);
                break;
            }
            case TOP: {
                this.drawTopRoundRect(canvas, paint, right, bottom);
                break;
            }
            case BOTTOM: {
                this.drawBottomRoundRect(canvas, paint, right, bottom);
                break;
            }
            case LEFT: {
                this.drawLeftRoundRect(canvas, paint, right, bottom);
                break;
            }
            case RIGHT: {
                this.drawRightRoundRect(canvas, paint, right, bottom);
                break;
            }
            case OTHER_TOP_LEFT: {
                this.drawOtherTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            }
            case OTHER_TOP_RIGHT: {
                this.drawOtherTopRightRoundRect(canvas, paint, right, bottom);
                break;
            }
            case OTHER_BOTTOM_LEFT: {
                this.drawOtherBottomLeftRoundRect(canvas, paint, right, bottom);
                break;
            }
            case OTHER_BOTTOM_RIGHT: {
                this.drawOtherBottomRightRoundRect(canvas, paint, right, bottom);
                break;
            }
            case DIAGONAL_FROM_TOP_LEFT: {
                this.drawDiagonalFromTopLeftRoundRect(canvas, paint, right, bottom);
                break;
            }
            case DIAGONAL_FROM_TOP_RIGHT: {
                this.drawDiagonalFromTopRightRoundRect(canvas, paint, right, bottom);
                break;
            }
            case BORDER_ALL: {
                final Path path = new Path();
                final Paint borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                path.addRect(new RectF((float) (this.margin + this.cornerWidth), (float) (this.margin + this.cornerWidth), right - this.cornerWidth, bottom - this.cornerWidth), Path.Direction.CW);
                if (this.cornerWidth > 0) {
                    canvas.drawPath(path, paint);
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
            case BORDER_TOP: {
                final Path path = new Path();
                final Paint borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                path.moveTo((float) this.margin, 0.0f);
                path.lineTo(right, 0.0f);
                path.close();
                if (this.cornerWidth > 0) {
                    canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right, bottom), paint);
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
            case BORDER_RIGHT: {
                final Path path = new Path();
                final Paint borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                path.moveTo(right, (float) this.margin);
                path.lineTo(right, bottom);
                path.close();
                if (this.cornerWidth > 0) {
                    canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right, bottom), paint);
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
            case BORDER_BOTTOM: {
                final Path path = new Path();
                final Paint borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                path.moveTo(right, bottom);
                path.lineTo(0.0f, bottom);
                path.close();
                if (this.cornerWidth > 0) {
                    canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right, bottom), paint);
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
            case BORDER_LEFT: {
                final Path path = new Path();
                final Paint borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                path.moveTo(0.0f, bottom);
                path.lineTo(0.0f, 0.0f);
                path.close();
                if (this.cornerWidth > 0) {
                    canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right, bottom), paint);
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
            default: {
                canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, right, bottom), (float) this.radius, (float) this.radius, paint);
                final Path path = new Path();
                final Paint borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setColor(this.color);
                borderPaint.setStrokeWidth((float) this.cornerWidth);
                path.addRoundRect(new RectF((float) this.margin, (float) this.margin, right, bottom), (float) this.radius, (float) this.radius, Path.Direction.CW);
                if (this.cornerWidth > 0) {
                    canvas.drawPath(path, borderPaint);
                    break;
                }
                break;
            }
        }
    }

    private void drawTopLeftRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), (float) (this.margin + this.radius), bottom), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, right, bottom), paint);
    }

    private void drawTopRightRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, (float) this.margin, right, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right - this.radius, bottom), paint);
        canvas.drawRect(new RectF(right - this.radius, (float) (this.margin + this.radius), right, bottom), paint);
    }

    private void drawBottomLeftRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, bottom - this.diameter, (float) (this.margin + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), bottom - this.radius), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, right, bottom), paint);
    }

    private void drawBottomRightRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, bottom - this.diameter, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right - this.radius, bottom), paint);
        canvas.drawRect(new RectF(right - this.radius, (float) this.margin, right, bottom - this.radius), paint);
    }

    private void drawTopRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, right, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), right, bottom), paint);
    }

    private void drawBottomRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, bottom - this.diameter, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right, bottom - this.radius), paint);
    }

    private void drawLeftRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, right, bottom), paint);
    }

    private void drawRightRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, (float) this.margin, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right - this.radius, bottom), paint);
    }

    private void drawOtherTopLeftRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, bottom - this.diameter, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(right - this.diameter, (float) this.margin, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right - this.radius, bottom - this.radius), paint);
    }

    private void drawOtherTopRightRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.margin, bottom - this.diameter, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) this.margin, right, bottom - this.radius), paint);
    }

    private void drawOtherBottomLeftRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, right, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(right - this.diameter, (float) this.margin, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), right - this.radius, bottom), paint);
    }

    private void drawOtherBottomRightRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, right, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) (this.margin + this.radius), right, bottom), paint);
    }

    private void drawDiagonalFromTopLeftRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF((float) this.margin, (float) this.margin, (float) (this.margin + this.diameter), (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF(right - this.diameter, bottom - this.diameter, right, bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) (this.margin + this.radius), right - this.diameter, bottom), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.diameter), (float) this.margin, right, bottom - this.radius), paint);
    }

    private void drawDiagonalFromTopRightRoundRect(final Canvas canvas, final Paint paint, final float right, final float bottom) {
        canvas.drawRoundRect(new RectF(right - this.diameter, (float) this.margin, right, (float) (this.margin + this.diameter)), (float) this.radius, (float) this.radius, paint);
        canvas.drawRoundRect(new RectF((float) this.margin, bottom - this.diameter, (float) (this.margin + this.diameter), bottom), (float) this.radius, (float) this.radius, paint);
        canvas.drawRect(new RectF((float) this.margin, (float) this.margin, right - this.radius, bottom - this.radius), paint);
        canvas.drawRect(new RectF((float) (this.margin + this.radius), (float) (this.margin + this.radius), right, bottom), paint);
    }

    public String toString() {
        return "RoundedTransformation(radius=" + this.radius + ", margin=" + this.margin + ", diameter=" + this.diameter + ", cornerType=" + this.cornerType.name() + ")";
    }

    public boolean equals(final Object o) {
        return o instanceof ColoredRoundedCornerBorders && ((ColoredRoundedCornerBorders) o).radius == this.radius && ((ColoredRoundedCornerBorders) o).diameter == this.diameter && ((ColoredRoundedCornerBorders) o).margin == this.margin && ((ColoredRoundedCornerBorders) o).cornerType == this.cornerType;
    }

    public int hashCode() {
        return "com.github.triniwiz.imagedemo.ColoredRoundedCornerBorders.1".hashCode() + this.radius * 10000 + this.diameter * 1000 + this.margin * 100 + this.cornerType.ordinal() * 10;
    }

    public void updateDiskCacheKey(@NonNull final MessageDigest messageDigest) {
        messageDigest.update(("com.github.triniwiz.imagedemo.ColoredRoundedCornerBorders.1" + this.radius + this.diameter + this.margin + this.cornerType).getBytes(ColoredRoundedCornerBorders.CHARSET));
    }

    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT,
        BORDER_ALL,
        BORDER_TOP,
        BORDER_RIGHT,
        BORDER_BOTTOM,
        BORDER_LEFT;
    }
}
