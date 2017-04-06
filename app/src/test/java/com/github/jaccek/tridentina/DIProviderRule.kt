package com.github.jaccek.tridentina

import android.content.Context
import android.content.res.AssetManager
import com.github.jaccek.tridentina.di.DIProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import org.mockito.Mockito

class DIProviderRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement =
            object : Statement() {
                override fun evaluate() {
                    val context = Mockito.mock(Context::class.java)
                    Mockito.`when`(context.assets).thenReturn(Mockito.mock(AssetManager::class.java))
                    DIProvider.init(context)
                    base.evaluate()
                }
            }
}
