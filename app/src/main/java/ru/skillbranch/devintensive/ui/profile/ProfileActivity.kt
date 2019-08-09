package ru.skillbranch.devintensive.ui.profile

import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_profile_constraint.*
import kotlinx.android.synthetic.main.activity_profile_constraint.btn_edit
import kotlinx.android.synthetic.main.activity_profile_constraint.et_about
import kotlinx.android.synthetic.main.activity_profile_constraint.et_first_name
import kotlinx.android.synthetic.main.activity_profile_constraint.et_last_name
import kotlinx.android.synthetic.main.activity_profile_constraint.et_repository
import kotlinx.android.synthetic.main.activity_profile_constraint.ic_eye
import kotlinx.android.synthetic.main.activity_profile_constraint.tv_nick_name
import kotlinx.android.synthetic.main.activity_profile_constraint.tv_rank
import kotlinx.android.synthetic.main.activity_profile_constraint.tv_rating
import kotlinx.android.synthetic.main.activity_profile_constraint.tv_respect
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.extensions.hideKeyboard
import ru.skillbranch.devintensive.models.Bender
import ru.skillbranch.devintensive.viewmodels.ProfileViewModel
import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.utils.Utils

class ProfileActivity : AppCompatActivity(){
    companion object{
        const val IS_EDIT_MODE = "IS_EDIT_MODE"
    }

    private lateinit var viewModel: ProfileViewModel

    var isEditMode = false
    lateinit var viewFields: Map<String, TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_constraint)
        initViews(savedInstanceState)
        initViewModel()
        val mode = savedInstanceState?.getBoolean("edit")
        if (mode != null) {
            isEditMode = mode
            showCurrentMode(isEditMode)
        }
        wr_repository.setErrorEnabled(false)


    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState!!.putBoolean("edit", isEditMode)
        super.onSaveInstanceState(outState)


    }

    fun noError(Github: String) : Boolean{
       if (Github == "")
           return true

        var github: String = Github

        val dropGithub: Int

        if (github.startsWith("https://github.com/")){
            dropGithub = 19
        }
        else if (github.startsWith("www.github.com/")){
            dropGithub = 15
        }
        else if (github.startsWith("https://www.github.com/")){
            dropGithub = 23
        }
        else
            return false

        github = github.drop(dropGithub)

        return (
                github != "enterprise" &&
                github != "features" &&
                github != "topics" &&
                github != "collections" &&
                github != "trending" &&
                github != "events" &&
                github != "marketplace" &&
                github != "pricing" &&
                github != "nonprofit" &&
                github != "customer-stories" &&
                github != "security" &&
                github != "login" &&
                github != "join" &&
                "/" !in github &&
                "." !in github
                )

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

        var githubSaveOrNot: Boolean = true

        et_repository.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                wr_repository.setErrorEnabled(!noError(s.toString()))
                Log.d("M_error", "error disabled/enabled in the text change  - $s")


                if (!noError(s.toString())){
                    wr_repository.error = "Невалидный адрес репозитория"
                    githubSaveOrNot = false
                }
                else{
                    githubSaveOrNot = true
                    wr_repository.setErrorEnabled(false)
                    Log.d("M_error", "error disabled")
                }

            }



        })



        btn_edit.setOnClickListener{
            if (isEditMode) {
                if (!githubSaveOrNot)
                    et_repository.setText(null)
                saveProfileInfo()
            }
            wr_repository.isErrorEnabled = false
            Log.d("M_error", "error disabled")

            isEditMode = !isEditMode
            showCurrentMode(isEditMode)
        }

        btn_switch_theme.setOnClickListener{
            viewModel.switchTheme()
        }

    }


    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.getProfileData().observe(this, Observer { updateUI(it) })
        viewModel.getTheme().observe(this, Observer { updateTheme(it) })

    }

    private fun updateTheme(mode: Int) {
        delegate.setLocalNightMode(mode)
    }

    private fun updateUI(profile: Profile?) {
        profile!!.toMap().also {
            for ((k,v) in viewFields) {
                 v.text = it[k].toString()}
        }
    }

    private fun saveProfileInfo(){
        Profile(
            firstName = et_first_name.text.toString(),
            lastName  = et_last_name.text.toString(),
            about = et_about.text.toString(),
            repository = et_repository.text.toString()
        ).apply {
            viewModel.saveProfileData(this)
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
        ic_eye.visibility = if(isEdit) View.GONE else View.VISIBLE
        with(btn_edit){
            val filter: ColorFilter? = if(isEdit){
            PorterDuffColorFilter(resources.getColor(R.color.color_accent, theme),
            PorterDuff.Mode.SRC_IN)
        }
            else
                null


        val icon = if (isEdit)
            resources.getDrawable(R.drawable.ic_save_black_24dp, theme)
        else
            resources.getDrawable(R.drawable.ic_edit_black_24dp, theme)

            background.colorFilter = filter
        setImageDrawable(icon)



        }
    }


}

