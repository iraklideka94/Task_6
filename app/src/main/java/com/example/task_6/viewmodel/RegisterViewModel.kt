package com.example.task_6.viewmodel

import androidx.lifecycle.ViewModel
import com.example.task_6.models.RegisterForm
import com.example.task_6.models.UserInfo
import com.example.task_6.network.RetrofitInstance
import com.example.task_6.utils.ResponseHandler
import kotlinx.coroutines.flow.flow

class RegisterViewModel: ViewModel() {

    fun getRegisterFlow(userInfo: UserInfo) = flow<ResponseHandler> {
        emit(ResponseHandler.Loader(isLoading = true))
        val response = RetrofitInstance.getAuthApi().getRegisterForm(userInfo)
        if (response.isSuccessful && response.body() != null) {
            emit(ResponseHandler.Success<RegisterForm>(response.body()!!))
        } else {
            emit(ResponseHandler.Error(response.errorBody()?.string() ?: "Error!"))
        }
        emit(ResponseHandler.Loader(isLoading = false))
    }

}