package com.github.jaccek.tridentina.data.rdp.specification.impl.assets

import android.content.res.AssetManager
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import io.reactivex.Single


interface AssetsSpecification<TYPE> : Specification<TYPE> {

    fun getResults(assetManager: AssetManager) : Single<Collection<TYPE>>
}