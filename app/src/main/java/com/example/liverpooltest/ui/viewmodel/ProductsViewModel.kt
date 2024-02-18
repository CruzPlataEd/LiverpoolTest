package com.example.liverpooltest.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.liverpooltest.domain.GetInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getInfoUseCase: GetInfoUseCase
): ViewModel() {

}