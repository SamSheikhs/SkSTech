package com.app.skstech.ui.auth.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.app.skstech.R
import com.app.skstech.base.Resource
import com.app.skstech.databinding.FragmentRegisterBinding
import com.app.skstech.provider.BindFragment
import com.app.skstech.ui.auth.model.User
import com.app.skstech.ui.auth.usecase.RegisterValidation
import com.app.skstech.ui.auth.viewmodel.MainViewModel
import com.app.skstech.util.LoadingDialogFragmenet
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class RegisterFragment constructor() : Fragment() {
    val mainBinding: FragmentRegisterBinding by BindFragment(R.layout.fragment_register)
    lateinit var loadingDialogFragmenet: LoadingDialogFragmenet
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loadingDialogFragmenet = LoadingDialogFragmenet()
        eventer()
        return mainBinding.root
    }

    private fun eventer() {
        mainBinding.regBtn.setOnClickListener {

            mainBinding.apply {
                val user = User(
                    nameEt.text.toString(),
                    emailEt.text.toString(),
                    mobileEt.text.toString()
                )
                viewModel.createUser(user, password = passwordEt.text.toString())
            }
        }

        lifecycleScope.launch {
            viewModel.register.collect {
                withContext(Dispatchers.Main) {
                    when (
                        it
                    ) {
                        is Resource.Error -> {
                            loadingDialogFragmenet.dismiss()
                            Toasty.error(
                                requireActivity(),
                                it.message.toString(),
                                Toast.LENGTH_LONG,
                                true
                            ).show()
                        }

                        is Resource.Loading -> {
                            loadingDialogFragmenet.show(
                                parentFragmentManager,
                                LoadingDialogFragmenet::class.java.name
                            )
                        }

                        is Resource.Success -> {
                            loadingDialogFragmenet.dismiss()
                            Log.d("TestDebugMsg", it.message.toString())
                            Toasty.success(requireActivity(), "Success!", Toast.LENGTH_SHORT, true)
                                .show();
                        }
                        else -> {}
                    }
                }
            }
        }



        lifecycleScope.launch {

            var errorShown =  false
            viewModel.validation.collect {
                mainBinding.apply {
                    if (it.fname is RegisterValidation.Failed && !errorShown) {
                        nameEt.apply {
                            error = it.fname.message
                            requestFocus()
                        }
                        errorShown =  true
                    }

                    if (it.email is RegisterValidation.Failed &&   !errorShown) {
                        emailEt.apply {
                            error = it.email.message
                        }
                        errorShown =  true
                    }

                    if (it.mobile is RegisterValidation.Failed  && !errorShown) {
                        mobileEt.apply {
                            error = it.mobile.message
                        }
                        errorShown =  true
                    }
                    if (it.password is RegisterValidation.Failed  && !errorShown) {
                        passwordEt.apply {
                            error = it.password.message
                        }
                        errorShown =  true

                    }
                }

            }
        }
    }
}