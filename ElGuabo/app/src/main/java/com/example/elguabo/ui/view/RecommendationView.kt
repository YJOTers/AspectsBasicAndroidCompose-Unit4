package com.example.elguabo.ui.view

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun RecommendationView(
    list: List<Recommendation>,
    clicked: (Recommendation)-> Unit,
    onBackPressed: ()-> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
){
    BackHandler { onBackPressed() }
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.short1))
    ){
        items(list){ item ->
            RecommendationItem(
                item = item,
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.short2))
                    .clickable { clicked(item) }
            )
        }
    }
}

@Composable
private fun RecommendationItem(
    item: Recommendation,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.short1))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center.apply {
                Arrangement.spacedBy(dimensionResource(id = R.dimen.short2))
            },
            modifier = modifier
        ) {
            Image(
                painter = painterResource(id = item.recommendationImageId),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(dimensionResource(id = R.dimen.medium1))
            )
            Column(
                verticalArrangement = Arrangement.Center.apply {
                    Arrangement.spacedBy(dimensionResource(id = R.dimen.short2)) },
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = stringResource(id = item.recommendationNameId),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = stringResource(id = item.hoursOfOperationId),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}