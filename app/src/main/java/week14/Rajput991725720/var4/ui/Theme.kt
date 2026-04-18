package week14.Rajput991725720.var4.ui
//Manasi Rajput
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple = Color(0xFF6A1B9A)

private val ColorScheme = darkColorScheme(
    primary = Purple
)
//adding the color scheme as required
@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        content = content
    )
}