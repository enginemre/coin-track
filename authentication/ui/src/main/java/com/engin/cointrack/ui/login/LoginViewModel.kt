package com.engin.cointrack.ui.login

import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<LoginViewState, LoginViewEvent>() {

    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun onEvent(event: LoginViewEvent) {
        when (event) {
            LoginViewEvent.NavigateBack -> sendEvent(LoginViewEvent.NavigateBack)
            LoginViewEvent.OnPasswordVisibilityClick -> setState { copy(showPassword = !showPassword) }
            LoginViewEvent.OnLoginClick -> onLoginClick()
            is LoginViewEvent.OnUserNameChange -> onUserNameChange(event.value)
            is LoginViewEvent.OnPasswordChange -> onPasswordChange(event.value)
        }
    }

    private fun onLoginClick() {
    }

    private fun onPasswordChange(value: String) {
        setState { copy(password = value) }
    }

    private fun onUserNameChange(value: String) {
        setState { copy(userName = value) }
    }
}

data class LoginViewState(
    val loading: Boolean = false,
    val userName: String = "",
    val password: String = "",
    val showPassword: Boolean = false,
) : IViewState
