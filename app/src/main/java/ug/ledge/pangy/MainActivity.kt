package ug.ledge.pangy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ug.ledge.pangy.ui.theme.PANGYTheme

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ug.ledge.pangy.screens.dashboard.FirstScreen

import ug.ledge.pangy.screens.dashboard.UserDetailScreen
import ug.ledge.pangy.screens.dashboard.UserList
import ug.ledge.pangy.screens.dashboard.viewModels.ProfileViewModel
import ug.ledge.pangy.screens.dashboard.viewModels.UserDetailViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PANGYTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxWidth()) {
                    FirstScreen()
//                    MainApp()
                }
            }
        }
    }
}


@Composable
fun MainApp(){
    //Plan to extract all route names as constants
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Dashboard" ){
        composable(route="Dashboard"){
                UserList{userId ->
                    navController.navigate("user_profile/$userId")
                    Log.d("IDPRINT", "$userId")
                }
        }
        composable(
            route = "user_profile/{userId}",
            arguments = listOf(navArgument("userId"){
                type= NavType.StringType
            })
        ){
            val viewModel: UserDetailViewModel = viewModel()
            UserDetailScreen(viewModel.userState.value)
            Log.d("IDPRINT", "User is ${viewModel.userState.value}")

        }
    }

}






//    val viewModel: PropertyViewModel = viewModel()
//    val rememberedProperties: MutableState<List<Property>> = remember { mutableStateOf(emptyList<Property>()) }
//    viewModel.getProperty { response ->
//        val properties = response
//        rememberedProperties.value= listOf( properties!!)
//
//    }
//    Text(text = rememberedProperties.value.toString())




@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    PANGYTheme {
        MainApp()
    }
}