package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_profile_constraint.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender

class ProfileActivity : AppCompatActivity(){
    companion object{
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_constraint)
    }



    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

    }

    private fun initViews(savedInstanceState: Bundle?){
        viewFields = mapOf(
            "nickname" to tv_nick_name,
            "rank" to tv_rank,
            "firstName" to et_first_name,
            "lastName" to et_last_name,
            "about" to et_about,
            "rating" to tv_rating,
            "respect" to tv_respect,
            "repository" to et_repository
        )

        btn_edit.setOnClickListener{
            Log.d("btn_edit", "Click on edit button")
            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }
    }

    private fun showCurrentMode(isEdit: Boolean) {
        val info = viewFields.filter { setOf("firstName", "lastName", "about", "repository").contains(it.key) }
        for ((_,v) in info){
            v as EditText
            v.isFocusable = isEdit
            v.isFocusableInTouchMode = isEdit
            v.isEnabled = isEdit
            v.background.alpha = if(isEdit) 255 else 0
        }
    }


}

