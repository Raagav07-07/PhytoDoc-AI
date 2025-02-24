package com.eg.phytodoc
import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eg.phytodoc.ui.theme.Bluef
import com.eg.phytodoc.ui.theme.Green1
import com.eg.phytodoc.ui.theme.Green2
import com.eg.phytodoc.ui.theme.Green3
import com.eg.phytodoc.ui.theme.OrangeF

import com.eg.phytodoc.ui.theme.Poppins
import com.eg.phytodoc.ui.theme.Purple40
import com.eg.phytodoc.ui.theme.PurpleGrey80
import com.eg.phytodoc.ui.theme.blue2
import com.eg.phytodoc.ui.theme.pinkf1
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController:NavController) {
    Scaffold {
         Box(modifier = Modifier
             .fillMaxSize()
             .background(Green2),
             ){
             Row(){
                 val navigationController = rememberNavController()
             IconButton(onClick = {
                 navController.navigate(Screens.Predict.screen) {

                 }
             }) {
                 Icon(imageVector = Icons.Default.Search, contentDescription ="Null", tint = Color.White )

             }
                 Text(text="Upload the photo", style=TextStyle(fontFamily = Poppins, fontSize = 20.sp),modifier = Modifier.padding(8.dp), color = Color.White)}
            Box(modifier = Modifier
                .padding(top = 55.dp)
                .fillMaxSize()
                .clip(
                    RoundedCornerShape(topStart = 35.dp,topEnd=35.dp)
                ).background(Color.White)

            ){
                val scrollState= rememberScrollState()
               Column(modifier=Modifier.verticalScroll(scrollState)){ Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                   card(R.drawable.apple,"Apple")
                   Spacer(modifier = Modifier.height(10.dp))
                   card(R.drawable.blueberry,"Blueberry")}
                   Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                       card(R.drawable.cherry,"Cherry")
                       Spacer(modifier = Modifier.height(10.dp))
                       card(R.drawable.corn,"Corn")}
                   Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                       card(R.drawable.grape,"Grape")
                       Spacer(modifier = Modifier.height(10.dp))
                       card(R.drawable.orange,"Orange")}
                   Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                       card(R.drawable.peach,"Peach")
                       Spacer(modifier = Modifier.height(10.dp))
                       card(R.drawable.pepperball,"Pepperball")}
                   Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                       card(R.drawable.potato,"Potato")
                       Spacer(modifier = Modifier.height(10.dp))
                       card(R.drawable.raspberry,"Raspberry")}
                   Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                       card(R.drawable.soybeans,"Soybeans")
                       Spacer(modifier = Modifier.height(10.dp))
                       card(R.drawable.squash,"Squash")}
                   Row(modifier=Modifier.padding(top = 25.dp,start=20.dp),horizontalArrangement = Arrangement.spacedBy(24.dp)){
                       card(R.drawable.strawberry,"Strawberry")
                       Spacer(modifier = Modifier.height(10.dp))
                       card(R.drawable.tomato,"Tomato") }}
            }
    }
    }
}
@Composable
fun card(image:Int,text:String){
    var showDialog by remember { mutableStateOf(false) }
    ElevatedCard(modifier = Modifier
        .height(199.dp)
        .width(164.dp)
        .clip(RoundedCornerShape(20.dp))
        , onClick = { showDialog = true}) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Green3
                )
                , horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter = painterResource(id = image),
                contentDescription = "Centered Image",
                modifier = Modifier
                    .height(164.dp)
                    .width(164.dp)
                    .clip(RoundedCornerShape(5.dp)), contentScale = ContentScale.Crop
            )
            Text(text=text,style=TextStyle(fontFamily = Poppins, fontSize = 20.sp), color = Color.White)
        }

    }
    if(showDialog){
        Dialog(onDismissRequest = { showDialog=false }) {
            Box( modifier= Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(blue2)
                .padding(16.dp)){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            val t:Description= getDescriptionForText(text)
            Text(text = t.desc,style=TextStyle(fontFamily = Poppins, fontSize = 20.sp))
            Button(onClick = { showDialog = false }, colors = ButtonDefaults.buttonColors(Green3)) {
                Text("Close")
            }
        }
            }
        }
    }
}
fun getDescriptionForText(text: String): Description {
    return when (text) {
        "Apple" -> Description.Apple
        "Blueberry" -> Description.Blueberry
        "Cherry" -> Description.Cherry
        "Corn" -> Description.Corn
        "Grape" -> Description.Grape
        "Orange" -> Description.Orange
        "Peach" -> Description.Peach
        "Pepperball" -> Description.Pepperball
        "Potato" -> Description.Potato
        "Soybeans" -> Description.Soybeans
        "Squash" -> Description.Squash
        "Raspberry" -> Description.Raspberry
        "Tomato" -> Description.Tomato
        "Strawberry" -> Description.Strawberry
        else -> Description.Apple
    }
}