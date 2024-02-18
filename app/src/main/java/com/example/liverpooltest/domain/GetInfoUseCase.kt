package com.example.liverpooltest.domain

import com.example.liverpooltest.data.ApiRepository
import javax.inject.Inject

class GetInfoUseCase @Inject constructor(private val repository : ApiRepository) {
    suspend operator fun invoke(page: Int, search: String, sortOption: String) = repository.getInfoApi(page,search, sortOption)
}