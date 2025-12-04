package view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun CodeEditor(
    code: String,
    onCodeChange: (String) -> Unit
) {
    TextField(
        value = code,
        onValueChange = onCodeChange,
        modifier = Modifier.fillMaxSize(),
        textStyle = TextStyle(
            fontFamily = FontFamily.Monospace,
            fontSize = 14.sp
        ),
        maxLines = Int.MAX_VALUE
    )
}