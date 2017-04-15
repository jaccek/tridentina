package com.github.jaccek.tridentina.data.rdp.repository.impl.assets

import com.github.jaccek.tridentina.di.DIProvider
import java.io.InputStream

class AssetManagerWrapper {
    private val assetManager = DIProvider.assetManager

    fun listFiles(path: String): Array<out String> =
            assetManager.list(path)

    fun openFile(filename: String, accessMode: Int): InputStream =
            assetManager.open(filename, accessMode)
}
