package com.github.jaccek.tridentina.di.module

import com.github.jaccek.tridentina.data.rdp.specification.base.prayer.AllPrayersSpecification
import com.github.jaccek.tridentina.data.rdp.specification.impl.assets.AllPrayersAssetsSpecification
import dagger.Module
import dagger.Provides

@Module
class PrayerAssetsSpecificationModule {

    @Provides
    fun provideAllPrayersSpecification(): AllPrayersSpecification {
        return AllPrayersAssetsSpecification()
    }
}