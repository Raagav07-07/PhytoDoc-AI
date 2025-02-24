package com.eg.phytodoc

sealed class Screens(val screen:String) {
    data object Home:Screens("home")
    data object Predict:Screens("Predict")
    data object Profile:Screens("profile")
}