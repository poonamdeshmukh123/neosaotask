package com.example.neosaotask.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager
{

    companion object {
        private const val PREF_NAME = "verification"
        fun getSharedPreferences(context: Context):SharedPreferences
        {
            val sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)
            return sharedPreferences
        }
        fun saveString(context: Context, key: String?, value: String?) {
            val editor: SharedPreferences.Editor = getSharedPreferences(context).edit()
            editor.putString(key, value)
            editor.apply()
        }
        fun removeString(context: Context,key:String)
        {
            val editor:SharedPreferences.Editor = getSharedPreferences(context).edit()
            editor.remove(key)
            editor.apply()
        }
        fun getString(context:Context,key:String):String?
        {
            if(getSharedPreferences(context).contains(key))

                return getSharedPreferences(context).getString(key,null)

            else
                return null
        }
fun removeAll(context:Context)
{
    val editor:SharedPreferences.Editor = getSharedPreferences(context).edit()
    editor.clear()
    editor.apply()
}

    }


}