package com.eg.phytodoc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eg.phytodoc.ui.theme.Green2
import com.eg.phytodoc.ui.theme.Green3
import com.eg.phytodoc.ui.theme.PhytoDocTheme
import com.eg.phytodoc.ui.theme.Poppins
import kotlinx.coroutines.launch

class MainActivity() : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedLanguage = this.intent?.getStringExtra("LANGUAGE") ?: LocaleHelper.getSavedLanguage(this)


        LocaleHelper.setAppLocale(this, selectedLanguage)
        enableEdgeToEdge()
        setContent {
            PhytoDocTheme {
                val navController = rememberNavController()
                Scaffold {

NavDrawer(selectedLanguage)

                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun NavDrawer(selectedLanguage:String) {
    val navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(

        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier
                .width(280.dp)
                ) {
                Column() {
                    Text(text = "", modifier = Modifier.padding(bottom = 16.dp))

                    HorizontalDivider()

                    NavigationDrawerItem(

                        label = { Text(text = "Home", style=TextStyle(fontFamily = Poppins, fontSize = 20.sp)) },
                        selected = false,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            navigationController.navigate(Screens.Home.screen) {
                                popUpTo(navigationController.graph.startDestinationId) { inclusive = true }
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Predict", style=TextStyle(fontFamily = Poppins, fontSize = 20.sp)) },
                        selected = false,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            navigationController.navigate(Screens.Predict.screen) {
                                popUpTo(navigationController.graph.startDestinationId) { inclusive = true }
                            }
                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "செடி Buddy",style= TextStyle(fontFamily = Poppins, fontSize = 25.sp)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Green2,
                        titleContentColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Default.Menu, contentDescription = "Open Drawer", tint = Color.White)
                        }
                    }
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                NavHost(navController = navigationController, startDestination = Screens.Home.screen) {
                    composable(Screens.Home.screen) { HomeScreen(navigationController) }
                    composable(Screens.Predict.screen) { Predict(selectedLanguage) }
                }
            }
        }
    }
}
