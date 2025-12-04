package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlin.io.path.absolutePathString
import kotlin.io.path.writeText
import kotlin.io.path.writer

class CodeViewModel {
    var code by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set

    fun onCodeChange(newCode: String) {
        code = newCode
    }

    fun runCode() {
        val file = kotlin.io.path.createTempFile("script", ".kts")
        file.writeText(code)
        val process = ProcessBuilder("kotlinc", "-script", file.absolutePathString())
            .redirectErrorStream(true)
            .start()

        result = process.inputStream.bufferedReader().readText()
    }
}