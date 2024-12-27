package com.app.gdgocunsrimobiledevsubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.gdgocunsrimobiledevsubmission.ui.theme.GDGOCUnsriMobileDevSubmissionTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GDGOCUnsriMobileDevSubmissionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    FindJackpot()
                }
            }
        }
    }
}

@Composable
fun FindJackpot() {
    var counter by remember { mutableStateOf(0) }
    var probabilitas by remember { mutableStateOf(0.01) }
    var isButtonEnabled by remember { mutableStateOf(true) }
    var NonJackpot by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Nilai: $counter",
            fontSize = 56.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = if (counter % 2 == 0) "Genap" else "Ganjil",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Probabilitas Jackpot: ${String.format("%.0f", probabilitas * 100)}%",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 24.dp),
            color = MaterialTheme.colorScheme.outline
        )

        if (isButtonEnabled) {
            Button(
                onClick = {
                    counter++
                    val isGanjil = counter % 2 != 0

                    if (isGanjil && counter > 10) {
                        if (Random.nextDouble() <= probabilitas) {
                            isButtonEnabled = false
                            showDialog = true
                        } else {
                            NonJackpot++
                            if (NonJackpot >= 1) {
                                probabilitas = minOf(probabilitas + 0.01, 0.05)
                            }
                        }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Cari Jackpot Anda!")
            }
        } else {
            Button(
                onClick = {
                    counter = 0
                    probabilitas = 0.01
                    isButtonEnabled = true
                    NonJackpot = 0
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Mulai Lagi")
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text(
                        text = "Jackpot! 🎉",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Selamat! Anda 'Jackpot' pada angka $counter",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Start
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text(
                            text = "OK",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GDGOCUnsriMobileDevSubmissionTheme {
        FindJackpot()
    }
}
