package com.app.skstech.ui.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.skstech.R
import com.app.skstech.databinding.FragmentAccountOptionBinding
import com.app.skstech.databinding.FragmentInfoBinding
import com.app.skstech.provider.BindFragment

class AccountOptionFragment :  Fragment() {
    val binding  : FragmentAccountOptionBinding by BindFragment(R.layout.fragment_account_option)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        eventer()
        return  binding.root
    }

    private fun eventer() {
        binding.apply {
            loginTv.setOnClickListener {
                findNavController().navigate(R.id.loginFragment)
            }

             registerTv.setOnClickListener {
                findNavController().navigate(R.id.registerFragment)
            }
        }
    }
}