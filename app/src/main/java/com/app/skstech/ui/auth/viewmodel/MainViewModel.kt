package com.app.skstech.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.skstech.base.Resource
import com.app.skstech.listeners.UserDataCreationListener
import com.app.skstech.ui.auth.model.User
import com.app.skstech.ui.auth.usecase.RegisterFieldState
import com.app.skstech.ui.auth.usecase.RegisterValidation
import com.app.skstech.ui.auth.usecase.validateEmail
import com.app.skstech.ui.auth.usecase.validateMobile
import com.app.skstech.ui.auth.usecase.validateName
import com.app.skstech.ui.auth.usecase.validatePassword
import com.app.skstech.util.DateUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<FirebaseUser>>(Resource.Unspecified())
    val register: Flow<Resource<FirebaseUser>> = _register

    private val _validation = Channel<RegisterFieldState>()
    val validation = _validation.receiveAsFlow()


    fun createUser(user: User, password: String) {
        if (userValidation(user, password)) {
            viewModelScope.launch {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    it.user?.let { fbUser ->

                        createUserDataFb(user, object : UserDataCreationListener {
                            override fun onSuccess() {
                                _register.value = Resource.Success(fbUser)
                            }
                            override fun onFailure(error: String) {
                                _register.value = Resource.Error(error)
                            }
                        })

                    }

                }.addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())
                }

        } else {
            val registerFieldState = RegisterFieldState(
                validateName(user.fname),
                validateEmail(user.email),
                validateMobile(user.mobile),
                validatePassword(password)
            )

            runBlocking {
                _validation.send(registerFieldState)
            }
        }
    }

    private fun createUserDataFb(fbUser: User, listener: UserDataCreationListener) {

        val additionalData = mapOf(
            "name" to fbUser.fname,
            "email" to fbUser.email,
            "mobile" to fbUser.mobile,
            "created_at" to DateUtils.getCurrentUniversaralDate()
        )

        val additionalDataRef = FirebaseDatabase.getInstance()
        val ref: DatabaseReference = additionalDataRef.getReference("users/${fbUser.mobile}")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    ref.setValue(additionalData).addOnSuccessListener {
                        // Handle success
                        listener.onSuccess()

                    }.addOnFailureListener {
                        listener.onFailure("Error Occurred")
                    }
                } else {

                    listener.onFailure("User with this mobile number already exists")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                listener.onFailure("Error Occurred")
            }
        })
    }

    private fun userValidation(user: User, password: String): Boolean {
        val nameValidate = validateName(user.fname)
        val mobileValidation = validateMobile(user.mobile)
        val emailValidation = validateEmail(user.email)
        val passVaidation = validatePassword(password)

        return (
                nameValidate is RegisterValidation.Success &&
                        mobileValidation is RegisterValidation.Success &&
                        emailValidation is RegisterValidation.Success &&
                        passVaidation is RegisterValidation.Success
                )
    }

}