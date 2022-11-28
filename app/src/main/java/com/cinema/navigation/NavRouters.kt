package com.cinema.navigation



sealed class NavRouters(val route:String){
    object PreviewScreen:NavRouters("preview")
    object Home: NavRouters("home")
    object Details: NavRouters("details")
}
