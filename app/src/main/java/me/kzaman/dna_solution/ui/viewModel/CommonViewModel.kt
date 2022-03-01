package me.kzaman.dna_solution.ui.viewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import me.kzaman.dna_solution.base.BaseViewModel
import me.kzaman.dna_solution.repository.CommonRepository
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    val repository: CommonRepository,
) : BaseViewModel(repository) {

}