package me.kzaman.dna_solution.ui.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import me.kzaman.dna_solution.base.BaseActivity
import me.kzaman.dna_solution.database.SharedPreferenceManager
import me.kzaman.dna_solution.databinding.ActivityDashboardBinding
import me.kzaman.dna_solution.ui.viewModel.CommonViewModel
import me.kzaman.dna_solution.utils.startNewActivityAnimation
import javax.inject.Inject


@AndroidEntryPoint
class DashboardActivity : BaseActivity(){
    @Inject
    lateinit var prefManager: SharedPreferenceManager

    private val viewModel by viewModels<CommonViewModel>()
    private lateinit var binding: ActivityDashboardBinding

    private var scrollRange = -1
    private var mIsTheTitleVisible = false
    private lateinit var rvHomeList: RecyclerView
    private lateinit var shimmerMenuPlaceholder: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initializeApp()
    }


    override fun initializeApp() {}
    override fun setToolbarTitle(title: String) {}


    fun performLogout() = lifecycleScope.launch {
        viewModel.logout()
        prefManager.clearAll()
        startNewActivityAnimation(AuthActivity::class.java)
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack();
        } else {
            super.onBackPressed()
        }
    }
}