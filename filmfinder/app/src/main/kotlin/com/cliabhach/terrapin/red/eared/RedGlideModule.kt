package com.cliabhach.terrapin.red.eared

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * A basic [Glide] module - we only need to configure logging from here.
 *
 * @author Philip Cohn-Cort
 */
@GlideModule
class RedGlideModule: AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setLogLevel(Log.INFO)
    }
}