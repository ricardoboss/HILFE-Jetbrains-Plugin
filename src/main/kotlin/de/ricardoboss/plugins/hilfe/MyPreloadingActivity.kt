package de.ricardoboss.plugins.hilfe

import com.intellij.openapi.application.PreloadingActivity
import com.intellij.openapi.progress.ProgressIndicator
import org.wso2.lsp4intellij.IntellijLanguageClient
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.RawCommandServerDefinition

class MyPreloadingActivity : PreloadingActivity() {
    override fun preload(indicator: ProgressIndicator) {
        IntellijLanguageClient.addServerDefinition(RawCommandServerDefinition("hil", arrayOf<String>("/Users/ricardo/repos/ricardoboss/HILFE/HILFE.LSP.Server/bin/Debug/net7.0/HILFE.LSP.Server")))
    }
}