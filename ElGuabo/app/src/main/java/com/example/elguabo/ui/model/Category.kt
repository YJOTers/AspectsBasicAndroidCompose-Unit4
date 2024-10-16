package com.example.elguabo.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    @DrawableRes val categoryImageId: Int,
    @StringRes val categoryNameId: Int,
    val recommendationList: List<Recommendation>,
    val recommendationCount: Int = recommendationList.count()
)