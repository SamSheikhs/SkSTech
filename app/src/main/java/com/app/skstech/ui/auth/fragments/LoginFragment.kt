package com.app.skstech.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.app.skstech.AuthRecicer
import com.app.skstech.R
import com.app.skstech.databinding.ActivityMainBinding
import com.app.skstech.databinding.FragmentLoginBinding
import com.app.skstech.provider.BindActivity
import com.app.skstech.provider.BindFragment
import com.app.skstech.ui.auth.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment constructor(): Fragment() {
    val mainBinding : FragmentLoginBinding by BindFragment(R.layout.fragment_login)
    private  val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _intializer()
        _eventer()
        return mainBinding.root
    }

    private fun _intializer() {

    }

    private fun _eventer() {
        mainBinding.accountTv.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
        }
    }
}