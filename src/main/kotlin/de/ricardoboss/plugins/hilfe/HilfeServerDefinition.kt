package de.ricardoboss.plugins.hilfe

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.application.PathManager
import com.intellij.openapi.extensions.PluginId
import org.wso2.lsp4intellij.client.connection.ProcessStreamConnectionProvider
import org.wso2.lsp4intellij.client.connection.StreamConnectionProvider
import org.wso2.lsp4intellij.client.languageserver.serverdefinition.LanguageServerDefinition
import java.nio.file.Paths
import java.util.*
import kotlin.io.path.pathString

class HilfeServerDefinition : LanguageServerDefinition() {
    init {
        ext = "hil"
        languageIds = Collections.emptyMap()
    }

    override fun createConnectionProvider(workingDir: String?): StreamConnectionProvider {
        return ProcessStreamConnectionProvider(buildCommandLine(), workingDir)
    }

    private fun getServerFilePath(): String {
        val pluginId = PluginId.getId("de.ricardoboss.plugins.hilfe")

        PluginManager.getInstance().findEnabledPlugin(pluginId)?.let {
            val pluginRootPath = PathManager.getPluginsPath()
            val pluginName = it.name

            return Paths.get(pluginRootPath, pluginName, "HILFE.LSP.Server.dll").pathString
        }

        throw Exception("Could not find plugin")
    }

    private fun buildCommandLine(): List<String> {
        return listOf(
            "dotnet",
            getServerFilePath(),
        )
    }
}