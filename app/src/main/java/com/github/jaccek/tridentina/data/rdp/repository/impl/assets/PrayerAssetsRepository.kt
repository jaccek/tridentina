package com.github.jaccek.tridentina.data.rdp.repository.impl.assets

import android.content.res.AssetManager
import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import com.github.jaccek.tridentina.data.rdp.specification.impl.assets.AssetsSpecification
import com.github.jaccek.tridentina.di.DIProvider
import io.reactivex.Single

class PrayerAssetsRepository : Repository<Prayer> {

    private var assetManager: AssetManager = DIProvider.assetManager

    override fun add(item: Prayer) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun update(item: Prayer) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun remove(item: Prayer) {
        throw UnsupportedOperationException("not implemented")
    }

    override fun query(specification: Specification<Prayer>): Single<Collection<Prayer>> {
        val assetsSpecification = specification as AssetsSpecification<Prayer>
        return assetsSpecification.getResults(assetManager)
    }
}
