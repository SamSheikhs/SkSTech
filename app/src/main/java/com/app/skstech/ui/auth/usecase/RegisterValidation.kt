package com.app.skstech.ui.auth.usecase

sealed class RegisterValidation() {
    data object Success : RegisterValidation()
    data class Failed(val message: String) : RegisterValidation()
}

data class RegisterFieldState(
    val fname: RegisterValidation,
    val email: RegisterValidation,
    val mobile: RegisterValidation,
    val password: RegisterValidation
)
