package com.app.skstech.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.skstech.R
import com.app.skstech.base.BaseActivity
import com.app.skstech.databinding.ActivityAuthBinding
import com.app.skstech.provider.BindActivity

class AuthActivity : BaseActivity() {
    val binding: ActivityAuthBinding by BindActivity(R.layout.activity_auth)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}