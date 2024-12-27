package com.app.gdgocunsrimobiledevsubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                    color = MaterialTheme.colorScheme.background
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
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = if (counter % 2 == 0) "Genap" else "Ganjil",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Probabilitas Jackpot: ${String.format("%.0f", probabilitas * 100)}%",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GDGOCUnsriMobileDevSubmissionTheme {
        FindJackpot()
    }
}
