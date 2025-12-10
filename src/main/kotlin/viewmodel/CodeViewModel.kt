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

    var hasErrors by mutableStateOf(false)
        private set

    private val viewModelScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    fun onCodeChange(newCode: String) {
        code = newCode
    }

    fun runCode() {
        if(isRunning) return

        isRunning = true
        hasErrors = false
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

                        if (line.contains("error") || line.contains("exception")) {
                            hasErrors = true
                            val regex = Regex(""".*\.kts:(\d+):.*""")
                            val match = regex.find(line)

                            val lineNumber = match?.groups?.get(1)?.value?.toIntOrNull()
                            println("error at line ${lineNumber}")
                        }
                    }
                }

                process.waitFor()
            } catch(e: Exception) {
                result += "\nError: ${e.message}"
                hasErrors = true
            } finally {
                isRunning = false
            }
        }
    }
}