package me.kzaman.dna_solution.repository

import me.kzaman.dna_solution.base.BaseRepository
import me.kzaman.dna_solution.network.api.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
) : BaseRepository(api) {

    suspend fun login(
        userName: String,
        password: String,
    ) = safeApiCall {
        api.login(userName, password)
    }
}