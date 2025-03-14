import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LanguageSelectionScreen(onLanguageSelected: (String) -> Unit) {
    var selectedLanguage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Choose Your Language")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            selectedLanguage = "en"
            onLanguageSelected(selectedLanguage)
        }) {
            Text("English")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            selectedLanguage = "ta"
            onLanguageSelected(selectedLanguage)
        }) {
            Text("தமிழ்")
        }
    }
}
