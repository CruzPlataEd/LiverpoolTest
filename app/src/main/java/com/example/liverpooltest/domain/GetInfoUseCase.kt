package com.example.liverpooltest.domain

import com.example.liverpooltest.data.ApiProvider
import com.example.liverpooltest.data.ApiRepository
import com.example.liverpooltest.data.model.Products
import com.example.liverpooltest.data.model.SortOptions
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(private val repository : ApiRepository, private val apiProvider: ApiProvider) {
    suspend fun invoke(page: Int, search: String, sortOption: String) = repository.getInfoApi(page,search, sortOption)
}