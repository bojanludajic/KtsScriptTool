package viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CodeViewModel {
    var code by mutableStateOf("")
        private set

    var result by mutableStateOf("")
        private set

    fun onCodeChange(newCode: String) {
        code = newCode
    }

    fun runCode() {
        //implement logic here!!!!
        result += code + "\n"
        result += "ran" + "\n"
    }
}