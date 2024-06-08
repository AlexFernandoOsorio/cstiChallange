package com.example.cstichallenge.features.register_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cstichallenge.R
import com.example.cstichallenge.features.common.UiState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val backgroundImage = painterResource(id = R.drawable.loginbackground)
    val scaffoldState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiState.SnackbarEvent -> {
                    scope.launch {
                        scaffoldState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
                is UiState.NavigateEvent -> {
                    scope.launch {
                        scaffoldState.showSnackbar(
                            message = "Login Successful",
                            duration = SnackbarDuration.Short
                        )
                    }
                    navController.popBackStack()
                    navController.navigate(event.route)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(scaffoldState) },
        content = { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Image(
                    painter = backgroundImage,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (loginState.isLoading) {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Sign up",
                        fontSize = 26.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left
                    )
                    Card(
                        modifier = Modifier
                            .padding(20.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Transparent),
                        shape = MaterialTheme.shapes.medium
                    )
                    {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            text = "Looks like you don't have an account.",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Left
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            text = "Let's create a new account for",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(8.dp),
                            value = emailState.text,
                            onValueChange = {
                                viewModel.setEmail(it)
                            },
                            label = { Text("Email") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                            ),
                            maxLines = 1,
                            isError = emailState.error != null,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = MaterialTheme.colorScheme.primary,
                                containerColor = MaterialTheme.colorScheme.background,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                                placeholderColor = MaterialTheme.colorScheme.tertiary.copy(
                                    alpha = 0.5f
                                ),
                                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary.copy(
                                    alpha = 0.5f
                                )
                            ),
                        )
                        if (emailState.error != "") {
                            Text(
                                text = emailState.error ?: "",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(8.dp),
                            value = passwordState.text,
                            onValueChange = {
                                viewModel.setPassword(it)
                            },
                            label = { Text("Password") },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            isError = passwordState.error != null,
                            trailingIcon = {
                                val image = if (passwordVisible)
                                    Icons.Filled.Info
                                else Icons.Filled.Lock
                                val description = if (passwordVisible) "Hide password" else "Show password"
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, description)
                                }
                            },
                            maxLines = 1,
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                textColor = MaterialTheme.colorScheme.primary,
                                containerColor = MaterialTheme.colorScheme.background,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedLeadingIconColor = MaterialTheme.colorScheme.primary,
                                placeholderColor = MaterialTheme.colorScheme.tertiary.copy(
                                    alpha = 0.5f
                                ),
                                unfocusedLeadingIconColor = MaterialTheme.colorScheme.tertiary.copy(
                                    alpha = 0.5f
                                )
                            )
                        )
                        if (passwordState.error != "") {
                            Text(
                                text = passwordState.error ?: "",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            text = "By selecting Agree and Continue below,",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Left,
                            fontFamily = FontFamily.SansSerif
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(color = Color.DarkGray)
                                ) {
                                    append("I agree to")
                                }
                                append(" ")
                                withStyle(
                                    style = SpanStyle(fontWeight = FontWeight.Bold,color = Color.Blue)
                                ) {
                                    append("Terms of Service and Privacy Policy")
                                }
                            },
                            fontFamily = FontFamily.SansSerif
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                viewModel.registerUser()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Agree and Continue",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(vertical = 6.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(navController = NavController(LocalContext.current))
}
