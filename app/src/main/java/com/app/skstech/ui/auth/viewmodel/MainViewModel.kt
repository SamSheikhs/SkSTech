package com.app.skstech.ui.auth.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.skstech.base.Resource
import com.app.skstech.ui.auth.model.User
import com.app.skstech.ui.auth.usecase.RegisterFieldState
import com.app.skstech.ui.auth.usecase.RegisterValidation
import com.app.skstech.ui.auth.usecase.validateEmail
import com.app.skstech.ui.auth.usecase.validateMobile
import com.app.skstech.ui.auth.usecase.validateName
import com.app.skstech.ui.auth.usecase.validatePassword
import com.app.skstech.util.Constants.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    val firebaseAuth: FirebaseAuth,
    val db: FirebaseFirestore
) : ViewModel() {

    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

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

                        createUserDataFb(user, fbUser.uid)

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

    private fun createUserDataFb(
        fbUser: User,
        uID: String
    ) {

        db.collection(USER_COLLECTION)
            .document(uID)
            .set(fbUser)
            .addOnSuccessListener {
                _register.value = Resource.Success(fbUser)

            }.addOnFailureListener {
                _register.value = Resource.Error(it.message.toString())
            }

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