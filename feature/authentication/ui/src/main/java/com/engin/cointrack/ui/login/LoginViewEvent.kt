package com.engin.cointrack.ui.login

import com.engin.cointrack.core.common.base.IViewEvent

sealed interface LoginViewEvent : IViewEvent {
    data object NavigateBack : LoginViewEvent
    data object NavigateHome : LoginViewEvent
    data object OnPasswordVisibilityClick : LoginViewEvent
    data object OnLoginClick : LoginViewEvent
    data class OnUserNameChange(val value: String) : LoginViewEvent
    data class OnPasswordChange(val value: String) : LoginViewEvent
}
