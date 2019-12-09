// 
// Decompiled by Procyon v0.5.36
// 

package com.bumptech.glide;

import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule;
import java.util.HashSet;
import java.util.Set;
import androidx.annotation.NonNull;
import android.util.Log;
import android.content.Context;
import com.github.triniwiz.imagecacheit.MyAppGlideModule;

final class GeneratedAppGlideModuleImpl extends GeneratedAppGlideModule
{
    private final MyAppGlideModule appGlideModule;
    
    public GeneratedAppGlideModuleImpl(final Context context) {
        this.appGlideModule = new MyAppGlideModule();
        if (Log.isLoggable("Glide", 3)) {
            Log.d("Glide", "Discovered AppGlideModule from annotation: com.github.triniwiz.imagecacheit.MyAppGlideModule");
            Log.d("Glide", "AppGlideModule excludes LibraryGlideModule from annotation: com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule");
        }
    }
    
    public void applyOptions(@NonNull final Context context, @NonNull final GlideBuilder builder) {
        this.appGlideModule.applyOptions(context, builder);
    }
    
    public void registerComponents(@NonNull final Context context, @NonNull final Glide glide, @NonNull final Registry registry) {
        this.appGlideModule.registerComponents(context, glide, registry);
    }
    
    public boolean isManifestParsingEnabled() {
        return this.appGlideModule.isManifestParsingEnabled();
    }
    
    @NonNull
    public Set<Class<?>> getExcludedModuleClasses() {
        final Set<Class<?>> excludedClasses = new HashSet<Class<?>>();
        excludedClasses.add(OkHttpLibraryGlideModule.class);
        return excludedClasses;
    }
    
    @NonNull
    GeneratedRequestManagerFactory getRequestManagerFactory() {
        return new GeneratedRequestManagerFactory();
    }
}
