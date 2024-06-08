package com.example.cstichallenge.features.login_screen

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cstichallenge.features.common.UiState
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.material3.*
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.cstichallange.R

import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    userArgument: String?,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value
    val loginState = viewModel.loginState.value
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    val backgroundImage = painterResource(id = R.drawable.loginbackground)
    val scaffoldState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    // Recuperamos valores del argumento User
    val email = userArgument?.split("+|+")?.get(0)
    val avatar = userArgument?.split("+|+")?.get(1).toString().replace("^", "/")
    val name = userArgument?.split("+|+")?.get(2).toString().replace("^", "/")
    email?.let { viewModel.setEmail(it) }
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
                    scaffoldState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Short
                    )
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
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(scaffoldState) },
        content = { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {

                Image(
                    painter = backgroundImage,
                    contentDescription = "User Avatar",
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
                        text = "Log in",
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
                        shape = MaterialTheme.shapes.medium,
                    )
                    {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .weight(0.3f)
                                    .fillMaxHeight()
                                    .padding(20.dp)
                            ) {
                                Column {
                                    Spacer(modifier = Modifier.height(24.dp))
                                    Image(
                                        painter = rememberAsyncImagePainter(avatar),
                                        contentDescription = "user",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(80.dp),
                                        contentScale = ContentScale.Crop,
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .weight(0.7f)
                                    .fillMaxHeight()
                            ) {
                                Column {
                                    Spacer(modifier = Modifier.height(24.dp))
                                    Text(
                                        text = name ?: "",
                                        fontSize = 14.sp,
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Left
                                    )
                                    Text(
                                        text = email ?: "",
                                        fontSize = 14.sp,
                                        color = Color.DarkGray,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Left
                                    )
                                }
                            }
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
                                // Localized description for accessibility services
                                val description =
                                    if (passwordVisible) "Hide password" else "Show password"

                                // Toggle button to hide or display password
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
                                color = MaterialTheme.colorScheme.error,
                                textAlign = TextAlign.End,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                viewModel.loginUser()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text(
                                text = "Continue",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                modifier = Modifier.padding(vertical = 6.dp)
                            )
                        }

                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextButton(onClick = {
                                //navigator.navigate(ForgotPasswordScreenDestination)
                            }) {
                                Text(
                                    text = "Forgot Password?",
                                    color = Color.Black,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    )
}