package com.app.skstech.ui.auth.fragments

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.skstech.AuthRecicer
import com.app.skstech.R
import com.app.skstech.databinding.FragmentInfoBinding
import com.app.skstech.provider.BindFragment

class InfoFragment constructor() : Fragment() {
    val binding  :  FragmentInfoBinding by BindFragment(R.layout.fragment_info)
    lateinit var progressDialg: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventer()
        return binding.root
    }

    private fun eventer() {
        binding.loginBtn.setOnClickListener {
            findNavController().navigate(R.id.accountOptionFragment)
        }
    }
}