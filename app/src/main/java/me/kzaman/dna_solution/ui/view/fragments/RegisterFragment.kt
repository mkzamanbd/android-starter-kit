package me.kzaman.dna_solution.ui.view.fragments

import android.os.Bundle
import me.kzaman.dna_solution.R
import me.kzaman.dna_solution.base.BaseFragment
import me.kzaman.dna_solution.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {
    override val layoutId: Int = R.layout.fragment_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
        mActivity = requireActivity()
    }
}