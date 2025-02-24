package com.eg.phytodoc

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eg.phytodoc.ui.theme.CustomFontFamily
import com.eg.phytodoc.ui.theme.PhytoDocTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhytoDocTheme {
                LoginScreen(onLoginSuccess = {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                })
            }
        }
    }
}
@Composable
fun LoginScreen(onLoginSuccess:()->Unit){
    var username by remember{
        mutableStateOf("")
    }
    var password by remember{
        mutableStateOf("")
    }
    var current= LocalContext.current.applicationContext
    Box(modifier=Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.screenshot_2025_02_18_221556), contentDescription = "null",contentScale= ContentScale.FillBounds,modifier=Modifier.matchParentSize().blur(radius=1000.dp))
    Column(modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text(text="PHYTODOC",style= TextStyle(fontFamily = CustomFontFamily, fontSize = 35.sp), fontWeight = FontWeight.Bold,color = Color.White)
        OutlinedTextField(value =username, onValueChange = {username=it}, colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, focusedIndicatorColor = Color.Blue, focusedLabelColor = Color.White, unfocusedIndicatorColor = Color.White, unfocusedContainerColor = Color.White),
            label={Text(text="UserName")},shape= RoundedCornerShape(10.dp),leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription ="null")
            })
        OutlinedTextField(value = password, onValueChange = {password=it},colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, focusedIndicatorColor = Color.Blue, focusedLabelColor = Color.White, unfocusedIndicatorColor = Color.White, unfocusedContainerColor = Color.White) , label={Text(text="Password")},shape= RoundedCornerShape(10.dp),leadingIcon = {
            Icon(imageVector = Icons.Default.Lock, contentDescription ="null",
            )
        }, visualTransformation = PasswordVisualTransformation())
        Button(onClick = {
            if(authenticate(username,password)){
                onLoginSuccess()
            }
            else{
                Toast.makeText(current,"Invalid Credentials",Toast.LENGTH_SHORT).show()
            }
        }, colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text ="Login")
        }
    }
}}
fun authenticate(username:String,password:String): Boolean {
    return username=="" && password==""
}

