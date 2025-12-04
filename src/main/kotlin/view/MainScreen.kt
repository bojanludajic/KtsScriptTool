package view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import viewmodel.CodeViewModel


@Composable
fun MainScreen(
    codeViewModel: CodeViewModel
) {
    // move to vm later

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CodeEditor(
            code = codeViewModel.code,
            onCodeChange = { codeViewModel.onCodeChange(it) },
            modifier = Modifier.weight(0.6f)
        )

        ExecutionBar(
            onRun = { codeViewModel.runCode() },
            modifier = Modifier
                .weight(0.1f)
        )

        ExecutionConsole(
            result = codeViewModel.result,
            modifier = Modifier.weight(0.3f)
        )
    }
}