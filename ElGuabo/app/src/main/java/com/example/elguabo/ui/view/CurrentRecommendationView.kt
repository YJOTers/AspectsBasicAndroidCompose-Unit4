package com.example.elguabo.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.elguabo.R
import com.example.elguabo.ui.model.Recommendation

@Composable
fun CurrentRecommendationView(
    item: Recommendation,
    onBackPressed: ()-> Unit,
    modifier: Modifier = Modifier
){
    BackHandler { onBackPressed() }
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(dimensionResource(id = R.dimen.short1))
    ){
        Image(
            painter = painterResource(id = item.recommendationImageId),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.short1)))
        Text(
            text = stringResource(id = R.string.hoursOfOperation),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(id = item.hoursOfOperationId),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.short1)))
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = stringResource(id = item.recommendationDescriptionId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}