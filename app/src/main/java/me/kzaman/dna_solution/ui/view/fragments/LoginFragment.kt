package me.kzaman.dna_solution.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import me.kzaman.dna_solution.R
import me.kzaman.dna_solution.base.BaseFragment
import me.kzaman.dna_solution.database.SharedPreferenceManager
import me.kzaman.dna_solution.databinding.FragmentLoginBinding
import me.kzaman.dna_solution.network.Resource
import me.kzaman.dna_solution.ui.view.activities.DashboardActivity
import me.kzaman.dna_solution.ui.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.kzaman.dna_solution.utils.LoadingUtils
import me.kzaman.dna_solution.utils.startNewActivityAnimation
import me.kzaman.dna_solution.utils.handleNetworkError
import me.kzaman.dna_solution.utils.hideSoftKeyboard
import me.kzaman.dna_solution.utils.visible
import me.kzaman.dna_solution.utils.enable
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private lateinit var binding: FragmentLoginBinding

    @Inject
    lateinit var prefManager: SharedPreferenceManager

    override val layoutId: Int = R.layout.fragment_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = requireContext()
        mActivity = requireActivity()
        loadingUtils = LoadingUtils(mContext)
    }

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        binding.lifecycleOwner = viewLifecycleOwner

        if (binding.passwordInputField.text.isNullOrEmpty()) {
            binding.loginButton.enable(false)
        }

        if (prefManager.isRemembered()) {
            binding.userNameInputField.setText(prefManager.getRememberUsername())
            binding.passwordInputField.setText(prefManager.getRememberPassword())
            binding.cbRememberMe.isChecked = true
        }

        viewModel.loginResponse.observe(viewLifecycleOwner) {
            loadingUtils.isLoading(it is Resource.Loading)
            if (it !is Resource.Loading) {
                binding.loginButton.isEnabled = true
                binding.loginButton.alpha = 1f
            }
            when (it) {
                is Resource.Success -> {
                    val response = it.value
                    if (response.responseCode == 200) {
                        if (response.data.user == null) {
                            binding.tvErrorMessage.visibility = View.VISIBLE
                            return@observe
                        }
                        prefManager.setLoginStatus(true)
                        prefManager.setTokenInformation(response.data.token)
                        response.data.user?.let { user -> prefManager.setUserInformation(user) }
                        mActivity.startNewActivityAnimation(DashboardActivity::class.java)
                    }
                }
                is Resource.Failure -> handleNetworkError(it, mActivity) {
                    login()
                }
                else -> Log.d("unknownError", "Unknown Error")
            }
        }

        binding.passwordInputField.addTextChangedListener {
            val userName = binding.userNameInputField.text.toString().trim()
            binding.loginButton.enable(userName.isNotEmpty() && it.toString().isNotEmpty())
        }


        binding.loginButton.setOnClickListener {
            login()
            hideSoftKeyboard(mContext, binding.passwordInputField)
        }

        binding.createAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun login() {
        binding.tvErrorMessage.visible(false)
        binding.loginButton.enable(false)
        val userName = binding.userNameInputField.text.toString().trim()
        val password = binding.passwordInputField.text.toString().trim()

        val rememberMe = binding.cbRememberMe
        if (rememberMe.isChecked) {
            if (prefManager.isRemembered()) {
                prefManager.updateRememberUserCredential(userName, password)
            } else {
                prefManager.rememberUserCredential(true, userName, password)
            }
        }

        viewModel.userLogin(userName, password)
    }
}