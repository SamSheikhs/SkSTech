package com.app.skstech.util

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class PreferenceUtility {
    companion object {

        private lateinit var preferences: SharedPreferences
        private val APP_PREFERENCE = "com.invesmate"
        fun init(mContext: Context) {
            preferences = mContext.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE)
        }

        fun logoutClear() {
        }

        /**
         * Save string
         *
         * @param key   the key
         * @param value the value
         */
        fun saveString(key: String, value: String) {

            val editor = preferences.edit()
            editor.putString(key, value)
            editor.apply()
        }

        /**
         * Gets string
         *
         * @param key      the key
         * @param defValue the def value
         * @return the string
         */
        fun getString(key: String, defValue: String = ""): String? {

            return preferences.getString(key, defValue)
        }

        /**
         * Save string
         *
         * @param key   the key
         * @param value the value
         */
        fun saveInt(key: String, value: Int) {

            val editor = preferences.edit()
            editor.putInt(key, value)
            editor.apply()
        }

        /**
         * Gets string
         *
         * @param key      the key
         * @param defValue the def value
         * @return the string
         */
        fun getInt(key: String, defValue: Int): Int? {

            return preferences.getInt(key, defValue)
        }

        /**
         * Save Boolean
         *
         * @param key   the key
         * @param value the value
         */
        fun saveBoolean(key: String, value: Boolean) {
            val editor = preferences.edit()
            editor.putBoolean(key, value)
            editor.apply()
        }

        /**
         * Gets string
         *
         * @param key      the key
         * @param defValue the def value
         * @return the string
         */
        fun getBoolean(key: String): Boolean {

            return preferences.getBoolean(key, false)
        }

        /**
         * Save Object
         *
         * @param key    the key
         * @param mObject the object
         */
        /*fun saveObject(key: String, mObject: Serializable) {
            try {

                val editor = preferences.edit()
                editor.putString(key, Serializer.serialize(mObject))
                editor.apply()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }*/

        fun saveObject(key: String, mObject: Any?) {
            saveString(key, Gson().toJson(mObject))
        }

        fun getObject(key: String, T: Class<*>): Any? {
            var getKeyValue = getString(key)
            return if (getKeyValue == "") {
                null
            } else {
                Gson().fromJson(getString(key), T)
            }
        }

        /**
         * Gets object
         *
         * @param key the key
         * @return the mObject
         */
        fun getObject(key: String): Any? {
            try {
                val mObject = preferences.getString(key, null)
                return if (mObject != null)
                    Serializer.deSerialize(mObject)
                else
                    null
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        fun clearAllData() {
            preferences.edit().clear().apply()
        }
    }
}