package com.engin.cointrack.ui.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import com.engin.cointrack.feature.authentication.domain.SignInUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase,
) : BaseViewModel<LoginViewState, LoginViewEvent>() {

    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun onEvent(event: LoginViewEvent) {
        when (event) {
            LoginViewEvent.NavigateBack -> sendEvent(LoginViewEvent.NavigateBack)
            LoginViewEvent.OnPasswordVisibilityClick -> setState { copy(showPassword = !showPassword) }
            LoginViewEvent.OnLoginClick -> onLoginClick()
            is LoginViewEvent.OnUserNameChange -> onEmailChange(event.value)
            is LoginViewEvent.OnPasswordChange -> onPasswordChange(event.value)
            else -> Unit
        }
    }

    private fun onLoginClick() {
        Log.d("LoginViewModel", "onLoginClick: ")
        signInUserUseCase(
            email = currentState.email,
            password = currentState.password,
        ).onEach { resource ->
            setState { copy(loading = resource is Resource.Loading) }
            when (resource) {
                Resource.Loading -> Unit
                is Resource.Error -> {
                    sendEvent(LoginViewEvent.ShowSnackBar(resource.exception?.message ?: "Error Occurred"))
                }
                is Resource.Success -> {
                    sendEvent(LoginViewEvent.NavigateHome)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onPasswordChange(value: String) {
        setState { copy(password = value) }
    }

    private fun onEmailChange(value: String) {
        setState { copy(email = value) }
    }
}

data class LoginViewState(
    val loading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
) : IViewState
