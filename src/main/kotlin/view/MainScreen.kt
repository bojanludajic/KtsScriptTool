package view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MainScreen() {
    // move to vm later
    var code by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CodeEditor(
            code,
            { code = it },
            modifier = Modifier.weight(0.6f)
        )

        ExecutionBar(
            onRun = {
                result = "Bla bla"
            },
            modifier = Modifier
                .weight(0.1f)
        )

        ExecutionConsole(
            result = result,
            modifier = Modifier.weight(0.3f)
        )
    }
}