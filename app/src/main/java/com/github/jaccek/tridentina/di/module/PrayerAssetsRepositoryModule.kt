package com.github.jaccek.tridentina.di.module

import android.content.Context
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.data.rdp.repository.impl.assets.PrayerAssetsRepository
import dagger.Module
import dagger.Provides

@Module
class PrayerAssetsRepositoryModule(val context: Context) {

    @Provides
    fun providePrayerAssetsRepository(): Repository<Prayer> {
        return PrayerAssetsRepository()
    }
}