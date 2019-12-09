// 
// Decompiled by Procyon v0.5.36
// 

package com.github.triniwiz.imagecacheit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

import java.io.File;

public class GlideApp {
    private GlideApp() {
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull final Context context) {
        return Glide.getPhotoCacheDir(context);
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull final Context context, @NonNull final String string) {
        return Glide.getPhotoCacheDir(context, string);
    }

    @NonNull
    public static Glide get(@NonNull final Context context) {
        return Glide.get(context);
    }

    @Deprecated
    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void init(final Glide glide) {
        Glide.init(glide);
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void init(@NonNull final Context context, @NonNull final GlideBuilder builder) {
        Glide.init(context, builder);
    }

    @VisibleForTesting
    @SuppressLint({"VisibleForTests"})
    public static void tearDown() {
        Glide.tearDown();
    }

    @NonNull
    public static GlideRequests with(@NonNull final Context context) {
        return (GlideRequests) Glide.with(context);
    }

    @NonNull
    public static GlideRequests with(@NonNull final Activity activity) {
        return (GlideRequests) Glide.with(activity);
    }

    @NonNull
    public static GlideRequests with(@NonNull final FragmentActivity activity) {
        return (GlideRequests) Glide.with(activity);
    }

    @NonNull
    public static GlideRequests with(@NonNull final Fragment fragment) {
        return (GlideRequests) Glide.with(fragment);
    }

    @Deprecated
    @NonNull
    public static GlideRequests with(@NonNull final android.app.Fragment fragment) {
        return (GlideRequests) Glide.with(fragment);
    }

    @NonNull
    public static GlideRequests with(@NonNull final View view) {
        return (GlideRequests) Glide.with(view);
    }
}
