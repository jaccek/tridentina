package com.github.jaccek.tridentina.data.rdp.specification.base.bookmark

import com.github.jaccek.tridentina.data.entity.Bookmark
import com.github.jaccek.tridentina.data.entity.PrayerId
import com.github.jaccek.tridentina.data.rdp.specification.base.Specification


interface BookmarksByPrayersIdSpecification : Specification<Bookmark> {

    fun withPrayersId(prayersId: Collection<PrayerId>) : BookmarksByPrayersIdSpecification
}