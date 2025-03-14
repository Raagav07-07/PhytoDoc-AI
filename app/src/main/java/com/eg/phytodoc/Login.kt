package com.eg.phytodoc

import LanguageSelectionScreen
import LocaleHelper
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eg.phytodoc.ui.theme.PhytoDocTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PhytoDocTheme {
                LanguageSelectionScreen { selectedLanguage ->
                    saveLanguageAndProceed(selectedLanguage)
                }
            }
        }
    }

    private fun saveLanguageAndProceed(language: String) {
        LocaleHelper.saveLanguage(this, language) // Save the selected language
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("LANGUAGE", language) // Pass language to MainActivity
        startActivity(intent)
        finish() // Close this activity
    }
}
