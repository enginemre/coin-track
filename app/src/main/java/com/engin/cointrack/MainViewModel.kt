package com.engin.cointrack

import androidx.lifecycle.ViewModel
import com.engin.cointrack.core.domain.GetUserLoginStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserLoginStatusUseCase: GetUserLoginStatusUseCase,
) : ViewModel() {
    fun isLogin() = getUserLoginStatusUseCase()
}
