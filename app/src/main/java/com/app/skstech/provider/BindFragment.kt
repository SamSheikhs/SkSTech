package com.app.skstech.provider

import android.os.Parcel
import android.os.Parcelable
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class BindFragment<in R : Fragment, out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : ReadOnlyProperty<R, T> {

    private var value: T? = null

    override operator fun getValue(thisRef: R, property: KProperty<*>): T {
        if (value == null) {
            value = DataBindingUtil.inflate<T>(
                thisRef.layoutInflater, layoutRes,
                thisRef.view?.rootView as ViewGroup?, false
            )
        }
        return value!!
    }
}