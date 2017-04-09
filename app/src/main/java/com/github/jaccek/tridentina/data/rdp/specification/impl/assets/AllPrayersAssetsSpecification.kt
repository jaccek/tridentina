package com.github.jaccek.tridentina.data.rdp.specification.impl.assets

import android.content.res.AssetManager
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.impl.assets.AssetsRepositoryException
import com.github.jaccek.tridentina.data.rdp.specification.base.prayer.AllPrayersSpecification
import com.github.jaccek.tridentina.data.rdp.specification.impl.assets.AssetsSpecification
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import io.reactivex.Observable
import io.reactivex.Single
import java.io.InputStreamReader

class AllPrayersAssetsSpecification : AllPrayersSpecification, AssetsSpecification<Prayer> {

    override fun getResults(assetManager: AssetManager): Single<Collection<Prayer>> {
        val gson = Gson()

        return Observable.fromIterable(assetManager.list(".").toList())
                .map { assetManager.open(it, AssetManager.ACCESS_BUFFER) }
                .map { JsonReader(InputStreamReader(it)) }
                .map { gson.fromJson<Prayer>(it, Prayer::class.java) }
                .switchIfEmpty(Observable.error(AssetsRepositoryException("Assets not found")))
                .toList()
                .map { it }
    }
}
