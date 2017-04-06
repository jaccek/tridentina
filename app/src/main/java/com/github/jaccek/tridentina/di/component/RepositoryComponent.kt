package com.github.jaccek.tridentina.di.component

import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.di.module.PrayerAssetsRepositoryModule
import dagger.Component

@Component(modules = arrayOf(
        PrayerAssetsRepositoryModule::class
))
interface RepositoryComponent {

    fun providePrayerRepository(): Repository<Prayer>
}