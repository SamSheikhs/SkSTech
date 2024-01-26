package com.app.skstech.listeners

interface UserDataCreationListener {
    fun onSuccess()
    fun onFailure(error: String)
}