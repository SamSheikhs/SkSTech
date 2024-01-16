package com.app.skstech.util

import java.io.*
import android.util.Base64



class Serializer {
    companion object {
        /**
         * Serialize string.
         *
         * @param object the object
         * @return the string
         */
        fun serialize(`object`: Serializable): String? {
            try {
                val baos = ByteArrayOutputStream()
                val oos = ObjectOutputStream(baos)
                oos.writeObject(`object`)
                oos.close()
                return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }

        /**
         * De serialize object.
         *
         * @param string the string
         * @return the object
         */
        fun deSerialize(string: String): Any? {
            try {
                val data = Base64.decode(string, Base64.DEFAULT)
                val ois = ObjectInputStream(ByteArrayInputStream(data))
                val `object` = ois.readObject()
                ois.close()
                return `object`
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return null
        }
    }
}