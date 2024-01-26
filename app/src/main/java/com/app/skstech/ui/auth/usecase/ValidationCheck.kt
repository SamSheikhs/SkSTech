package com.app.skstech.ui.auth.usecase

import android.util.Patterns
import com.app.skstech.util.CommonUtils

fun validateEmail(email : String) :  RegisterValidation{
    if (email.isEmpty()){
        return  RegisterValidation.Failed("Email cannot be Empty")
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
        return  RegisterValidation.Failed("Invalid Email")
    }
    return  RegisterValidation.Success
}

fun  validatePassword(pass :  String): RegisterValidation {
    if (pass.isEmpty()){
        return  RegisterValidation.Failed("Password cannot be Empty")
    }
    if (pass.length < 6){
        return  RegisterValidation.Failed("Password Cannot be less than 6 characters")
    }
    return  RegisterValidation.Success
}


fun validateMobile(mobile  : String): RegisterValidation {
     val isError  =  CommonUtils.isValidMobileNumber(mobile)
     if (!isError){
         return RegisterValidation.Failed("Enter valid mobile number")
     }
    return RegisterValidation.Success
}


fun validateName(name :  String): RegisterValidation {
    if (name.isEmpty() || name.length < 3){
        return  RegisterValidation.Failed("Enter valid name")
    }
    return  RegisterValidation.Success
}