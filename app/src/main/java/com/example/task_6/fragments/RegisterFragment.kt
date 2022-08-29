package com.example.task_6.fragments

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.task_6.BaseFragment
import com.example.task_6.databinding.FragmentRegisterBinding
import com.example.task_6.models.UserInfo
import com.example.task_6.utils.FragmentResUtils
import com.example.task_6.utils.ResponseHandler
import com.example.task_6.viewmodel.RegisterViewModel
import com.lukabaia.classwork_6.fragments.RegisterFragmentDirections
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun viewCreated() {

        onClickListeners()

    }

    private fun onClickListeners() {
        binding.btnRegister.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getRegisterFlow(getUserInfo()).collect {
                    when (it) {
                        is ResponseHandler.Success<*> -> {
                            buildFragmentResult()
                            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                        }
                        is ResponseHandler.Error -> {
                            Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                        }
                        is ResponseHandler.Loader -> {
                            binding.progressBar.isVisible = it.isLoading
                        }
                    }
                }
            }
        }
    }

    private fun buildFragmentResult() {
        setFragmentResult(
            requestKey = FragmentResUtils.AUTH_KEY,
            result = bundleOf(
                FragmentResUtils.EMAIL to binding.etEmail.text.toString(),
                FragmentResUtils.PASSWORD to binding.etPassword.text.toString()
            )
        )
    }

    private fun getUserInfo() = UserInfo(
        binding.etEmail.text.toString(),
        binding.etPassword.text.toString()
    )

}