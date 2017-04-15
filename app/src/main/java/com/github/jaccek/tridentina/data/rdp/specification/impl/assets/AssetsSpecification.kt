package com.github.jaccek.tridentina.data.rdp.specification.impl.assets

import com.github.jaccek.tridentina.data.rdp.repository.impl.assets.AssetManagerWrapper
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import io.reactivex.Single

interface AssetsSpecification<TYPE> : Specification<TYPE> {

    fun getResults(assetManager: AssetManagerWrapper) : Single<Collection<TYPE>>
}
