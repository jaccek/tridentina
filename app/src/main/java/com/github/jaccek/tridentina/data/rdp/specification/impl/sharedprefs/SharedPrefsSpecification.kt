package com.github.jaccek.tridentina.data.rdp.specification.impl.sharedprefs

import android.content.SharedPreferences
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification
import io.reactivex.Single


interface SharedPrefsSpecification<TYPE> : Specification<TYPE> {

    fun getResults(sharedPreferences: SharedPreferences) : Single<Collection<TYPE>>
}