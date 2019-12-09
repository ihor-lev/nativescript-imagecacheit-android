// 
// Decompiled by Procyon v0.5.36
// 

package com.bumptech.glide;

import com.github.triniwiz.imagecacheit.GlideRequests;
import android.content.Context;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.Lifecycle;
import androidx.annotation.NonNull;
import com.bumptech.glide.manager.RequestManagerRetriever;

final class GeneratedRequestManagerFactory implements RequestManagerRetriever.RequestManagerFactory
{
    @NonNull
    public RequestManager build(@NonNull final Glide glide, @NonNull final Lifecycle lifecycle, @NonNull final RequestManagerTreeNode treeNode, @NonNull final Context context) {
        return new GlideRequests(glide, lifecycle, treeNode, context);
    }
}
