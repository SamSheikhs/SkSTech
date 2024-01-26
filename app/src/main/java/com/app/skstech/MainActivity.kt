package com.app.skstech

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.app.skstech.databinding.ActivityMainBinding
import com.app.skstech.provider.BindActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AuthRecicer {
    val binding: ActivityMainBinding by BindActivity(R.layout.activity_main)
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        inital()
        eventer()
    }

    private fun eventer() {
    }

    @SuppressLint("CommitTransaction")
    private fun inital() {




//        navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        navController = navHostFragment.navController

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
//        navController = navHostFragment.navController



//        val navHost = mainBinding.fragmentContainerView as NavHostFragment
//        val navController = navHost.navController
//        val navGraph = navController.navInflater.inflate(R.navigation.home_main_graph)
//        navGraph.setStartDestination(R.id.action_infoFragment_to_accountOptionFragment)

//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        // Replace InfoFragment with your actual fragment
//        val infoFragment = InfoFragment(this)
//        // Use the container view ID (R.id.fragContainer) for the fragment transaction
//        fragmentTransaction.add(mainBinding.fragContainer.id, infoFragment)
//        fragmentTransaction.commit()

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onRegister() {
    }

    override fun onLogin() {
    }
}

interface AuthRecicer {
    fun onRegister()
    fun onLogin()
}

