package view

import androidx.compose.material.Text
import androidx.compose.runtime.*


@Composable
fun MainScreen() {
    // move to vm later
    var code by remember { mutableStateOf("") }

    CodeEditor(
        code,
        { code = it }
    )
}