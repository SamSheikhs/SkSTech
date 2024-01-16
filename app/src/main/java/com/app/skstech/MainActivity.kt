package com.app.skstech

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.skstech.databinding.ActivityMainBinding
import com.app.skstech.provider.BindActivity
import com.app.skstech.ui.auth.InfoFragment

class MainActivity : AppCompatActivity() {
    val mainBinding  :  ActivityMainBinding by BindActivity(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inital()
    }

    @SuppressLint("CommitTransaction")
    private fun inital() {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Replace InfoFragment with your actual fragment
        val infoFragment = InfoFragment()

        // Use the container view ID (R.id.fragContainer) for the fragment transaction
        fragmentTransaction.add(mainBinding.fragContainer.id, infoFragment)
        fragmentTransaction.commit()

    }
}