package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.io.path.absolutePathString
import kotlin.io.path.writeText

class CodeViewModel {
    var code by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set

    var isRunning by mutableStateOf(false)
        private set

    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun onCodeChange(newCode: String) {
        code = newCode
    }

    fun runCode() {
        isRunning = true
        result = ""

        viewModelScope.launch {
            try {
                val file = kotlin.io.path.createTempFile("script", ".kts")
                file.writeText(code)

                val process = ProcessBuilder("kotlinc", "-script", file.absolutePathString())
                    .redirectErrorStream(true)
                    .start()

                val outputBuilder = StringBuilder()
                process.inputStream.bufferedReader().useLines { lines ->
                    lines.forEach { line ->
                        outputBuilder.appendLine(line)
                        result = outputBuilder.toString()
                    }
                }
            } catch(e: Exception) {
                result += "\nError: ${e.message}"
            } finally {
                isRunning = false
            }
        }
    }
}