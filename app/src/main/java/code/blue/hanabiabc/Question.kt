package code.blue.hanabiabc

import android.media.Image
import androidx.annotation.StringRes

data class Question(@StringRes val textResId: Int, val answer: Boolean, val icon: Int, val choice_1: String, val choice_2: String)