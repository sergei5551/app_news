package ru.application.news_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

import ru.application.news_app.presentation.navigation.MainNav
import ru.application.news_app.presentation.ui.theme.NewsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val f = Firebase.firestore
        setContent {
            NewsAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPdding ->
                    MainContent(
                        modifier = Modifier.padding(innerPdding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
){
    MainNav(navHostController = rememberNavController(), modifier = modifier)
}

@Preview(showBackground = true)@Composable
fun GreetingPreview(){
    NewsAppTheme {
        MainContent()
    }
}
