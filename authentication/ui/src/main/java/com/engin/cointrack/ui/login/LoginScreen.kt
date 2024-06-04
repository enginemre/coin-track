package com.engin.cointrack.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.engin.cointrack.authentication.ui.R
import com.engin.cointrack.designsystem.component.CoinTrackTextField
import kotlinx.coroutines.flow.Flow

@Composable
fun LoginRoute(
    navigateBack: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    EventCollector(
        navigateBack = navigateBack,

        uiEvent = viewModel.uiEvent,
    )

    LoginScreen(
        onViewEvent = viewModel::onEvent,
        uiState = uiState,
    )
}

@Composable
fun LoginScreen(
    uiState: LoginViewState,
    onViewEvent: (LoginViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val focusManager = LocalFocusManager.current
            Column {
                val userNameInteractionSource = remember { MutableInteractionSource() }
                val userNameIsFocused by userNameInteractionSource.collectIsFocusedAsState()
                CoinTrackTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = uiState.userName,
                    interactionSource = userNameInteractionSource,
                    onValueChange = { onViewEvent(LoginViewEvent.OnUserNameChange(it)) },
                    label = {
                        Text(text = stringResource(R.string.email))
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = stringResource(R.string.email),
                            tint = if (userNameIsFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next, keyboardType = KeyboardType.Email),
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                )
                Spacer(modifier = Modifier.height(18.dp))
                val passwordInteractionSource = remember { MutableInteractionSource() }
                val passwordIsFocused by passwordInteractionSource.collectIsFocusedAsState()
                CoinTrackTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    value = uiState.password,
                    label = {
                        Text(text = stringResource(R.string.password))
                    },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Lock,
                            tint = if (passwordIsFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                            contentDescription = stringResource(R.string.password),
                        )
                    },
                    interactionSource = passwordInteractionSource,
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .clickable(
                                    role = Role.Button,
                                    onClick = remember { { onViewEvent(LoginViewEvent.OnPasswordVisibilityClick) } },
                                ),
                            imageVector = if (uiState.showPassword.not()) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null,
                            tint = if (passwordIsFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                        )
                    },
                    visualTransformation = if (uiState.showPassword.not()) PasswordVisualTransformation() else VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { onViewEvent(LoginViewEvent.OnPasswordChange(it)) },
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    onClick = remember { { onViewEvent(LoginViewEvent.OnLoginClick) } },
                ) {
                    Text(text = stringResource(R.string.login))
                }
            }
        }
    }
}

@Composable
fun EventCollector(
    navigateBack: () -> Unit,
    uiEvent: Flow<LoginViewEvent>,
) {
    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                LoginViewEvent.NavigateBack -> navigateBack()
                else -> Unit
            }
        }
    }
}
