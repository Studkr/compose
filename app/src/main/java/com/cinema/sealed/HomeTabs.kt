package com.cinema.sealed

import com.cinema.R

sealed class HomeTabs(val icon: Int, val tabName: String) {
    object Movie : HomeTabs(R.drawable.ic_movie, "Films")
    object Serials : HomeTabs(R.drawable.ic_serials, "Serials")

    companion object {
        fun returnList(): List<HomeTabs> = listOf(
            Movie, Serials
        )
    }
}
