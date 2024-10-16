package com.example.elguabo.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.elguabo.R
import com.example.elguabo.ui.model.Category
import com.example.elguabo.ui.model.Recommendation
import com.example.elguabo.ui.viewModel.MyCity
import com.example.elguabo.ui.viewModel.MyCityVM

enum class ViewType{ OnlyList, ListAndDetail}
enum class ViewSelected{ MyCity, Category, Recommendation }

@Composable
fun MyCityView(
    windowsSize: WindowWidthSizeClass,
    onBackPressed: ()-> Unit,
){
    val vmMyCity = MyCityVM()
    val uiState by vmMyCity.uiState.collectAsState()
    val contentType = when(windowsSize){
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> ViewType.OnlyList
        WindowWidthSizeClass.Expanded -> ViewType.ListAndDetail
        else -> ViewType.OnlyList
    }
    Scaffold(
        topBar = { MyCityAppBar(
            title = stringResource(uiState.titleId),
            viewSelected = uiState.viewSelected,
            windowsSize = windowsSize,
            onBackPressed = { onBack(vmMyCity, uiState) }
        ) }
    ) { innerPadding ->
        if(contentType == ViewType.ListAndDetail){
            MyCityListAndDetail(
                list1 = uiState.categoryList,
                clicked1 = {
                    vmMyCity.setCurrentRecommendationList(it.recommendationList)
                    vmMyCity.setTitleId(it.categoryNameId)
                    vmMyCity.setViewSelected(ViewSelected.Category)
                },
                list2 = uiState.recommendationList,
                clicked2 = {
                    vmMyCity.setCurrentRecommendation(it)
                    vmMyCity.setTitleId(it.recommendationNameId)
                    vmMyCity.setViewSelected(ViewSelected.Recommendation)
                },
                item = uiState.currentRecommendation,
                onBack = onBackPressed,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = innerPadding
            )
        }else{
            when(uiState.viewSelected){
                ViewSelected.MyCity -> {
                    CategoryView(
                        list = uiState.categoryList,
                        clicked = {
                            vmMyCity.setCurrentRecommendationList(it.recommendationList)
                            vmMyCity.setTitleId(it.categoryNameId)
                            vmMyCity.setViewSelected(ViewSelected.Category)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = innerPadding
                    )
                }
                ViewSelected.Category -> {
                    RecommendationView(
                        list = uiState.recommendationList,
                        clicked = {
                            vmMyCity.setCurrentRecommendation(it)
                            vmMyCity.setTitleId(it.recommendationNameId)
                            vmMyCity.setViewSelected(ViewSelected.Recommendation)
                        },
                        onBackPressed = { onBack(vmMyCity, uiState) },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = innerPadding
                    )
                }
                ViewSelected.Recommendation -> {
                    CurrentRecommendationView(
                        item = uiState.currentRecommendation,
                        onBackPressed = { onBack(vmMyCity, uiState) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MyCityAppBar(
    title: String,
    viewSelected: ViewSelected,
    windowsSize: WindowWidthSizeClass,
    onBackPressed: ()-> Unit,
    modifier: Modifier = Modifier
){
    val isNotMyCityView = viewSelected != ViewSelected.MyCity &&
            windowsSize != WindowWidthSizeClass.Expanded
    TopAppBar(
        title = { Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge) },
        navigationIcon = {
            if(isNotMyCityView) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }else{
                Box {}
            }
        },
        modifier = modifier
    )
}

@Composable
private fun MyCityListAndDetail(
    list1: List<Category>,
    clicked1: (Category)-> Unit,
    list2: List<Recommendation>,
    clicked2: (Recommendation)-> Unit,
    item: Recommendation,
    onBack: ()-> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues
){
    Row(modifier = modifier){
        CategoryView(
            list = list1,
            clicked = clicked1,
            contentPadding = contentPadding,
            modifier = Modifier.weight(1f)
        )
        RecommendationView(
            list = list2,
            clicked = clicked2,
            onBackPressed = onBack,
            contentPadding = contentPadding,
            modifier = Modifier.weight(1f)
        )
        CurrentRecommendationView(
            item = item,
            onBackPressed = onBack,
            modifier = Modifier.weight(1f)
        )
    }
}

private fun onBack(vm: MyCityVM, uiState: MyCity){
    val category = uiState.categoryList
        .find { it.recommendationList == uiState.recommendationList }
    val index = uiState.categoryList.indexOf(category)
    when(uiState.viewSelected){
        ViewSelected.Recommendation -> {
            vm.setViewSelected(ViewSelected.Category)
            vm.setTitleId(uiState.categoryList[index].categoryNameId)
        }
        ViewSelected.Category -> {
            vm.setViewSelected(ViewSelected.MyCity)
            vm.setTitleId(R.string.app_name)
        }
        ViewSelected.MyCity -> {}
    }
}