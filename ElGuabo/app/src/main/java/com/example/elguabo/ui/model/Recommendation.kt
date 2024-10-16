package com.example.elguabo.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    @DrawableRes val recommendationImageId: Int,
    @StringRes val recommendationNameId: Int,
    @StringRes val recommendationDescriptionId: Int,
    @StringRes val hoursOfOperationId: Int
)