package com.github.jaccek.tridentina.data.rdp.specification.base.prayer

import com.github.jaccek.tridentina.data.entity.Prayer
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification


interface PrayerByIdSpecification : Specification<Prayer> {

    val prayerId: PrayerId

    fun withPrayerId(prayerId: PrayerId)
}