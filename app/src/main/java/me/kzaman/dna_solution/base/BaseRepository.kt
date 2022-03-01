package me.kzaman.dna_solution.base

import me.kzaman.dna_solution.network.BaseApi
import me.kzaman.dna_solution.interfaces.SafeApiCall

abstract class BaseRepository(
    private val api: BaseApi,
) : SafeApiCall {
    suspend fun logout() = safeApiCall {
        api.logout()
    }
}