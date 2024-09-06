import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import ui.MainScreen
import ui.config.CipherTheme

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Crypto"
    ) {
        CipherTheme {
            Navigator(MainScreen())
        }
    }
}
