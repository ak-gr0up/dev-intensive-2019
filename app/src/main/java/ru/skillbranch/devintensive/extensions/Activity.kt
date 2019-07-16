package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val view: View = if (currentFocus == null) View(this) else currentFocus
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
fun Activity.isKeyboardOpened(): Boolean{
    var rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)
    val leftArea: Int = rect.height()
    val windowHeight: Int = this.resources.displayMetrics.heightPixels
    return when(windowHeight - leftArea > 250){true -> true; false -> false}
}
fun Activity.isKeyboardClosed(): Boolean{
    return when(isKeyboardOpened()){true -> false; false -> true}
}