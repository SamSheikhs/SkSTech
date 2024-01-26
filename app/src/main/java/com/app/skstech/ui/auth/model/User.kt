package com.app.skstech.ui.auth.model

data class User(
    val fname : String,
    val email : String,
    val mobile : String,
    var imagePath :  String = ""
){
    constructor() :  this("","","", "")
}