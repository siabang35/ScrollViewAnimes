package com.example.scrollview.ui.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Anime{
    @StringRes val name: Int,
    val availableCourses: Int,
    @DrawableRes val imageRes: Int

}