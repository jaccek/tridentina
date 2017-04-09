package com.github.jaccek.tridentina.data.rdp.specification.base.bookmark

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification


interface BookmarkByPrayerIdSpecification : Specification<Bookmark> {

    fun withPrayerId(prayerId: PrayerId) : BookmarkByPrayerIdSpecification
}