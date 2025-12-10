import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import view.MainScreen
import viewmodel.CodeViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Script Engine"
    ) {
        val codeViewModel = CodeViewModel()

        MainScreen(codeViewModel)
    }
}
