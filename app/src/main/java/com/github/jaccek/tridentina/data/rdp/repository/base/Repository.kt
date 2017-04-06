package com.github.jaccek.tridentina.data.rdp.repository.base

import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import io.reactivex.Single


interface Repository<TYPE> {

    fun add(item: TYPE)

    fun update(item: TYPE)

    fun remove(item: TYPE)

    fun query(specification: Specification<TYPE>): Single<Collection<TYPE>>
}