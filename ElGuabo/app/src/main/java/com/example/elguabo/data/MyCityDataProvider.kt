package com.example.elguabo.data

import com.example.elguabo.R
import com.example.elguabo.ui.model.Category
import com.example.elguabo.ui.model.Recommendation

object MyCityDataProvider {
    fun getCategory() : List<Category>{
        return listOf(
            Category(
                categoryImageId = R.drawable.restaurant,
                categoryNameId = R.string.titleCategory1,
                recommendationList = getRecommendation().subList(0, 3)
            ),
            Category(
                categoryImageId = R.drawable.park,
                categoryNameId = R.string.titleCategory2,
                recommendationList = getRecommendation().subList(3, 6)
            ),
            Category(
                categoryImageId = R.drawable.supermarket,
                categoryNameId = R.string.titleCategory3,
                recommendationList = getRecommendation().subList(6, 9)
            )
        )
    }

    private fun getRecommendation() : List<Recommendation>{
        return listOf(
            Recommendation(
                recommendationImageId = R.drawable.restaurant1,
                recommendationNameId = R.string.recommendation1_Category1,
                recommendationDescriptionId = R.string.descriptionRecommendation1,
                hoursOfOperationId = R.string.hoursOfOperation1
            ),
            Recommendation(
                recommendationImageId = R.drawable.restaurant2,
                recommendationNameId = R.string.recommendation2_Category1,
                recommendationDescriptionId = R.string.descriptionRecommendation2,
                hoursOfOperationId = R.string.hoursOfOperation2
            ),
            Recommendation(
                recommendationImageId = R.drawable.restaurant3,
                recommendationNameId = R.string.recommendation3_Category1,
                recommendationDescriptionId = R.string.descriptionRecommendation3,
                hoursOfOperationId = R.string.hoursOfOperation3
            ),
            Recommendation(
                recommendationImageId = R.drawable.park1,
                recommendationNameId = R.string.recommendation1_Category2,
                recommendationDescriptionId = R.string.descriptionRecommendation4,
                hoursOfOperationId = R.string.hoursOfOperation6
            ),
            Recommendation(
                recommendationImageId = R.drawable.park2,
                recommendationNameId = R.string.recommendation2_Category2,
                recommendationDescriptionId = R.string.descriptionRecommendation5,
                hoursOfOperationId = R.string.hoursOfOperation6
            ),
            Recommendation(
                recommendationImageId = R.drawable.park3,
                recommendationNameId = R.string.recommendation3_Category2,
                recommendationDescriptionId = R.string.descriptionRecommendation6,
                hoursOfOperationId = R.string.hoursOfOperation6
            ),
            Recommendation(
                recommendationImageId = R.drawable.supermarket1,
                recommendationNameId = R.string.recommendation1_Category3,
                recommendationDescriptionId = R.string.descriptionRecommendation7,
                hoursOfOperationId = R.string.hoursOfOperation4
            ),
            Recommendation(
                recommendationImageId = R.drawable.supermarket2,
                recommendationNameId = R.string.recommendation2_Category3,
                recommendationDescriptionId = R.string.descriptionRecommendation8,
                hoursOfOperationId = R.string.hoursOfOperation5
            ),
            Recommendation(
                recommendationImageId = R.drawable.supermarket3,
                recommendationNameId = R.string.recommendation3_Category3,
                recommendationDescriptionId = R.string.descriptionRecommendation9,
                hoursOfOperationId = R.string.hoursOfOperation4
            )
        )
    }
}