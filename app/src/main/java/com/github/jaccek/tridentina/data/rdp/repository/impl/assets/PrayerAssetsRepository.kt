package com.github.jaccek.tridentina.data.rdp.repository.impl.assets

import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.rdp.repository.base.Repository
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import com.github.jaccek.tridentina.data.rdp.specification.impl.assets.AssetsSpecification
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PrayerAssetsRepository : Repository<Prayer> {

    private var assetManager = AssetManagerWrapper()

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
                .subscribeOn(Schedulers.computation())
    }
}
