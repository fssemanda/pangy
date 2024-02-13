package ug.ledge.pangy.screens.dashboard
//
//import android.graphics.drawable.Icon
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CircularProgressIndicator
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.modifier.modifierLocalConsumer
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import coil.transform.CircleCropTransformation
//import ug.ledge.pangy.R
//import ug.ledge.pangy.screens.dashboard.viewModels.ProfileViewModel
//import ug.ledge.pangy.ui.theme.md_theme_light_onPrimaryContainer
//import ug.ledge.pangy.ui.theme.md_theme_light_primary
//import androidx.compose.ui.res.painterResource
//@Composable
//fun FirstScreen(){
//    Column {
//        TopSection()
//        Spacer(modifier = Modifier.height(20.dp))
//        MidSection()
//    }
//
//}
//
//@Composable
//fun TopSection(){
//    Row(modifier = Modifier
//        .padding(2.dp)
//        .fillMaxWidth()
//        .border(
//            border = BorderStroke(
//                0.dp, Color.Black
//            )
//        ),
//        verticalAlignment = Alignment.Top,
//        horizontalArrangement = Arrangement.SpaceBetween){
//        val vm: ProfileViewModel = viewModel()
//        val username = "fssemanda"
//
//        LaunchedEffect(username) {
//            vm.returnUserDetails(username)
//        }
//
//        if (vm.isLoading.value) {
//            // Show a loading indicator
//            Row (modifier= Modifier
//                .padding(10.dp)
//                .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center
//            ){
//                CircularProgressIndicator()
//            }
//        } else {
//            // Render the user data
//            vm.userState.value?.let {
//                Text(text = "Welcome,  ${it.firstName}",
//                    style=TextStyle(color = Color.Gray, fontWeight = FontWeight.Bold,
//                        fontSize = 18.sp,
//                    ), modifier = Modifier.padding(start=10.dp, top = 40.dp)
//                )
//            }
//        }
//
//        vm.userState.value?.let {
////            Text(text = "Welcome,  ${it.firstName}",
////                style=TextStyle(color = Color.Gray, fontWeight = FontWeight.Bold,
////                    fontSize = 18.sp,
////                ), modifier = Modifier.padding(start=10.dp, top = 40.dp)
////                )
////            Text(text = "${it.lastName}")
//
//            Card(
//
//                modifier = Modifier.padding(16.dp),
//                shape = CircleShape,
//                border = BorderStroke(width = 0.dp, color = md_theme_light_primary)
//            ) {
//                AsyncImage(
//                    model = ImageRequest.Builder(LocalContext.current)
//                        .data(it?.profile_pic)
//                        .transformations(CircleCropTransformation()).crossfade(true)
//                        .build(),
//
////                  placeholder = painterResource(R.drawable.avator),
//                    contentDescription = "", contentScale = ContentScale.Crop,
//
//                    modifier = Modifier
//                        .size(72.dp)
//
//                )
//            }
//        }
//
//
//
////            Text(text= "Hello ${mytext.toString()}")
//    }
//
//
//
//}
//
//
//@Composable
//fun MidSection(){
//    Row(modifier= Modifier
//        .padding(10.dp)
//        .fillMaxWidth(),
//        verticalAlignment = Alignment.Top,
//        horizontalArrangement = Arrangement.SpaceAround
//    ) {
////
////    Card(){
//
//        Column(horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Surface( modifier = Modifier.background(
//                color = md_theme_light_onPrimaryContainer, shape = RoundedCornerShape(10.dp)
//            )) {
//                IconButton(onClick = { /* Handle click here */ },
//
//                    ) {
//                    Icon(
//                        painter = painterResource(R.drawable.payments_fill0_wght400_grad0_opsz24),
//                        contentDescription = "Favorite",
//                        tint = md_theme_light_primary
//                    )
//                }
//            }
//
//            Text(text = "Payment") // Text below the icon
//        }
//
////    }
//        Card(modifier = Modifier.height(100.dp)
//            , shape = RoundedCornerShape(10.dp)) {
//            Text(text = "Balance Data")
//        }
//        Card(modifier = Modifier.height(100.dp)
//            , shape = RoundedCornerShape(10.dp)
//        ) {
//            Text(text="Other data")
//        }
//
//    }
//}
