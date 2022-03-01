package me.kzaman.dna_solution.data.response

import me.kzaman.dna_solution.data.model.UserDataModel


data class ProfileResponse(
    val code: Int,
    val status: String,
    val data: UserDataModel,
)