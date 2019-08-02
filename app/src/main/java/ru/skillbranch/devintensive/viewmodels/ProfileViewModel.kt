package ru.skillbranch.devintensive.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.skillbranch.devintensive.repositories.PreferencesRepository
import ru.skillbranch.devintensive.models.Profile

class ProfileViewModel : ViewModel(){
    val repository: PreferencesRepository = PreferencesRepository
    val profileData = MutableLiveData<Profile>()

    init {
        profileData.value = repository.getProfile()
    }

    fun getProfileData():LiveData<Profile> = profileData

    fun saveProfileData(profile:Profile){
        repository.saveProfile(profile)
        profileData.value = profile
    }
}