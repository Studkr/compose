package com.cinema.navigation



sealed class NavRouters(val route:String){
    object Home: NavRouters("home")
    object Details: NavRouters("details")
}
