package com.example.task_6.network

import com.example.task_6.models.LoginForm
import com.example.task_6.models.RegisterForm
import com.example.task_6.models.UserInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun getLoginForm(@Body userInfo: UserInfo): Response<LoginForm>

    @POST("register")
    suspend fun getRegisterForm(@Body userInfo: UserInfo): Response<RegisterForm>

}