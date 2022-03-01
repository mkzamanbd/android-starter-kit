package me.kzaman.dna_solution.repository

import me.kzaman.dna_solution.base.BaseRepository
import me.kzaman.dna_solution.network.api.CommonApi
import javax.inject.Inject

class CommonRepository @Inject constructor(
    private val api: CommonApi,
) : BaseRepository(api) {

}