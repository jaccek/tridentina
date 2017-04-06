package com.github.jaccek.tridentina.di

import android.content.Context
import com.github.jaccek.tridentina.di.component.DaggerRepositoryComponent
import com.github.jaccek.tridentina.di.module.PrayerAssetsRepositoryModule


object DIProvider {
    lateinit private var context: Context

    fun init(context: Context) {
        DIProvider.context = context
    }

    val repositoryComponent by lazy {
        DaggerRepositoryComponent.builder()
                .prayerAssetsRepositoryModule(PrayerAssetsRepositoryModule(context))
                .build()
    }

    val assetManager by lazy {
        context.assets
    }
}