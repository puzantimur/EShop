package com.example.eshop.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.eshop.domain.model.Detail
import com.example.domain.eshop.domain.repository.Repository
import com.example.eshop.presentation.ui.utils.Lce
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    private val _detailFlow = MutableStateFlow<Lce<Detail>>(Lce.Loading)

    val detailFlow: Flow<Lce<Detail>> = _detailFlow.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getDetails().onSuccess {
                _detailFlow.value = Lce.Content(it)
            }.onFailure {
                _detailFlow.value = Lce.Error(it)
            }

        }
    }
}
