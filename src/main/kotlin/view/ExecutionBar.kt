package view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExecutionBar(
    onRun: () -> Unit,
    modifier: Modifier = Modifier,
    isRunning: Boolean
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            Button(
                onClick = { onRun() },
                enabled = !isRunning
            ) {
                Text("Run")
            }

            Spacer(modifier = Modifier.width(16.dp))

            if (isRunning) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
        }
    }
}