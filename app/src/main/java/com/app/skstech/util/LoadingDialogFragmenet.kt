package com.app.skstech.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.app.skstech.R

class LoadingDialogFragmenet :  DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view  =  inflater.inflate(R.layout.loader,container,false)
        dialog?.setCanceledOnTouchOutside(false)

//        val semiTransparentColor = Color.parseColor("#80000000") // Change the alpha value as needed
//
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(semiTransparentColor))
//
//        dialog?.window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )

        return view
    }
}