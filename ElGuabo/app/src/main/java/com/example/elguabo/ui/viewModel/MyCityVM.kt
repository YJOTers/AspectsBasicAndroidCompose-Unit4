package com.example.elguabo.ui.viewModel

import androidx.lifecycle.ViewModel
import com.example.elguabo.R
import com.example.elguabo.data.MyCityDataProvider
import com.example.elguabo.ui.model.Category
import com.example.elguabo.ui.model.Recommendation
import com.example.elguabo.ui.view.ViewSelected
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MyCityVM : ViewModel(){
    private val _uiState = MutableStateFlow(MyCity())
    val uiState = _uiState.asStateFlow()

    override fun onCleared() {
        resetUiState()
    }

    fun setTitleId(value: Int){
        _uiState.update { it.copy(titleId = value) }
    }

    fun setViewSelected(value: ViewSelected){
        _uiState.update { it.copy(viewSelected = value) }
    }

    fun setCurrentRecommendationList(list: List<Recommendation>){
        _uiState.update { it.copy(recommendationList = list) }
    }

    fun setCurrentRecommendation(value: Recommendation){
        _uiState.update { it.copy(currentRecommendation = value) }
    }

    private fun resetUiState(){
        _uiState.value = MyCity()
    }
}

data class MyCity(
    val titleId: Int = R.string.app_name,
    val viewSelected: ViewSelected = ViewSelected.MyCity,
    val categoryList: List<Category> = MyCityDataProvider.getCategory(),
    val recommendationList: List<Recommendation> =
        MyCityDataProvider.getCategory()[0].recommendationList,
    val currentRecommendation: Recommendation =
        MyCityDataProvider.getCategory()[0].recommendationList[0]
)