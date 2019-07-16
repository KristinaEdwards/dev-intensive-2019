package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard(view: View? = null) {
    val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val windowToken = view?.windowToken ?: currentFocus?.windowToken ?: return
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}