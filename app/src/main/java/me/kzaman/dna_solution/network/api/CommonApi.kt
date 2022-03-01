package me.kzaman.dna_solution.network.api

import me.kzaman.dna_solution.data.response.DefaultResponse
import me.kzaman.dna_solution.data.response.ProfileResponse
import me.kzaman.dna_solution.network.BaseApi
import retrofit2.http.GET

interface CommonApi : BaseApi {

    @GET("user")
    suspend fun getUserProfile(): ProfileResponse

    @GET("mobile/get-sales-user-wise-all-customer-list")
    suspend fun getAllCustomers(): DefaultResponse
}