package com.github.jaccek.tridentina.di

import android.content.Context
import com.github.jaccek.tridentina.di.component.DaggerRepositoryComponent
import com.github.jaccek.tridentina.di.component.DaggerSpecificationComponent
import com.github.jaccek.tridentina.di.module.PrayerAssetsRepositoryModule
import com.github.jaccek.tridentina.di.module.PrayerAssetsSpecificationModule


object DIProvider {
    val PREFERENCES_NAME = "preferences"

    lateinit private var context: Context

    fun init(context: Context) {
        DIProvider.context = context
    }

    val repositoryComponent by lazy {
        DaggerRepositoryComponent.builder()
                .prayerAssetsRepositoryModule(PrayerAssetsRepositoryModule(context))
                .build()
    }

    val specificationComponent by lazy {
        DaggerSpecificationComponent.builder()
                .prayerAssetsSpecificationModule(PrayerAssetsSpecificationModule())
                .build()
    }

    val assetManager by lazy {
        context.assets
    }

    val sharedPreferences by lazy {
        context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }
}