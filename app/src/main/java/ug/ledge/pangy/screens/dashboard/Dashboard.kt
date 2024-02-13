package ug.ledge.pangy.screens.dashboard

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import ug.ledge.pangy.R
import ug.ledge.pangy.screens.dashboard.viewModels.ProfileViewModel
import ug.ledge.pangy.ui.theme.md_theme_light_onPrimaryContainer
import ug.ledge.pangy.ui.theme.md_theme_light_primary
import androidx.compose.ui.res.painterResource
import ug.ledge.pangy.Transactions.TransactionsViewModel
import ug.ledge.pangy.ui.theme.md_theme_light_onPrimary

@Composable
fun FirstScreen(){
    Column {
        TopSection()
//        Spacer(modifier = Modifier.height(10.dp))
        MidSection()
//        Spacer(modifier = Modifier.height(10.dp))
        CardSection()
        Headers()
        UserTransactions()
    }

}

@Composable
fun TopSection(){
    Row(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth()
//        .border(border = BorderStroke(color = Color.Black, width = 2.dp))
//        .border(border = BorderStroke(0.dp, Color.Black))
        ,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween){
        val vm: ProfileViewModel = viewModel()
        val username = "fssemanda"

        LaunchedEffect(username) {
            vm.returnUserDetails(username)
        }

        if (vm.isLoading.value) {
            // Show a loading indicator
            Row (modifier= Modifier
                .padding(10.dp)
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                CircularProgressIndicator()
            }
        } else {
            Column {


                // Render the user data
                vm.userState.value?.let {
                    Text(
                        text = "Welcome,  ${it.firstName}",
                        style = TextStyle(
                            color = Color.Gray, fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                        ), modifier = Modifier.padding(start = 10.dp, top = 20.dp)
                    )
//                    Text(
//                        text = "Active",
//                        style = TextStyle(
//                            color = Color.White, fontWeight = FontWeight.Bold,
//                            fontSize = 18.sp,
//                        ), modifier = Modifier
//                            .padding(start = 10.dp, top = 20.dp)
//                            .background(
//                                color = md_theme_light_primary,
//                                shape = RoundedCornerShape(15.dp)
//                            )
//                            .padding(10.dp)
//                    )
                }
            }
        }

        vm.userState.value?.let {
//            Text(text = "Welcome,  ${it.firstName}",
//                style=TextStyle(color = Color.Gray, fontWeight = FontWeight.Bold,
//                    fontSize = 18.sp,
//                ), modifier = Modifier.padding(start=10.dp, top = 40.dp)
//                )
//            Text(text = "${it.lastName}")

            Card(

                modifier = Modifier.padding(16.dp),
                shape = CircleShape,
                border = BorderStroke(width = 0.dp, color = md_theme_light_primary)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(it?.profile_pic)
                        .transformations(CircleCropTransformation()).crossfade(true)
                        .build(),

//                  placeholder = painterResource(R.drawable.avator),
                    contentDescription = "", contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .size(72.dp)

                )
            }
        }



//            Text(text= "Hello ${mytext.toString()}")
    }



}


@Composable
fun MidSection(){
    Row(modifier= Modifier
        .padding(10.dp)
        .fillMaxWidth()
//        .border(border = BorderStroke(color = Color.Black, width = 2.dp))
            ,
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier.padding(8.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(5.dp)
//                border = BorderStroke(width = 0.dp, color = md_theme_light_primary)
            ) {
                IconButton(
                    onClick = { /* Handle click here */ },
                    modifier = Modifier.padding(2.dp)

                ) {
                    Icon(
                        painter = painterResource(R.drawable.payments_fill0_wght400_grad0_opsz24),
                        contentDescription = "Item",
                        tint = md_theme_light_primary,
                        modifier = Modifier.size(24.dp)

                    )
                }
            }

            Text(
                text = "Deposit",
                style = TextStyle(
                    color = Color.Gray, fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
                ),
            ) // Text below the icon
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(

                modifier = Modifier.padding(8.dp),
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(5.dp)
//                border = BorderStroke(width = 0.dp, color = md_theme_light_primary)
            ) {
                IconButton(
                    onClick = { /* Handle click here */ },
                    modifier = Modifier.padding(2.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.account_balance_fill0_wght400_grad0_opsz24),
                        contentDescription = "Favorite",
                        tint = md_theme_light_primary,
                        modifier = Modifier.size(24.dp)

                    )
                }
            }

            Text(
                text = "Account",
                style = TextStyle(
                    color = Color.Gray, fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
                ),
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(

                modifier = Modifier
                    .padding(8.dp)
                    ,
                shape = CircleShape,
                elevation = CardDefaults.cardElevation(5.dp)
//                border = BorderStroke(width = 0.dp, color = md_theme_light_primary)
            ) {
                IconButton(
                    onClick = { /* Handle click here */ },
                    modifier = Modifier
                        .padding(2.dp)
                        .background(color = Color.White, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.query_stats_fill0_wght400_grad0_opsz24),
                        contentDescription = "Favorite",
                        tint = md_theme_light_primary,
                        modifier = Modifier.size(24.dp)

                    )
                }
            }

            Text(
                text = "Performance",
                style = TextStyle(
                    color = Color.Gray, fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
                ),
//                modifier = Modifier.padding(start=10.dp, top = 40.dp)
            ) // Text below the icon
        }
    }

    }
@Composable
fun CardSection() {

    Row() {
        val painter = painterResource(id = R.drawable.sky2)
        val description = "Rooms a"
        val title = "Rentals available"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            MyImageCard(painter = painter, contentDescription = "Card", title = "Account Status")

        }


        }
    }



@Composable
fun MyImageCard(
    painter: Painter,
    contentDescription:  String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    )
    {
        Box(modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()){
            Image(painter = painter, contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black
                        ),
                        startY = 300f
                    )
                ))
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
                contentAlignment = Alignment.TopStart){
                Text(text="Available Credits", style = TextStyle(color=Color.White
                    , fontWeight = FontWeight.Bold, fontSize = 12.sp
                )
                )
            }
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            contentAlignment = Alignment.CenterStart){
                Text(text="UGX 712,000.00", style = TextStyle(color=Color.White
                , fontWeight = FontWeight.Bold, fontSize = 26.sp
                ))
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ){
                Text(title, style = TextStyle(color = Color.Gray, fontSize = 20.sp))
//                Text(contentDescription, style = TextStyle(color = Color.Gray, fontSize = 20.sp))
            }
        }

    }

}

@Composable
fun UserTransactions(){


    Row {


    val transactionsViewModel: TransactionsViewModel = viewModel()

    transactionsViewModel.returnUserTransactions("tenant1")
    val transactionListing=transactionsViewModel.userTransactionState.value

    transactionListing.let {
        LazyColumn(modifier=Modifier.padding(start = 10.dp, end = 10.dp),content = {
            items(transactionListing){

                TransactionDataItem(it)
//                TransactionDataItem(it)

            }
        })
    }
    }
}

@Composable
fun Headers(){
    Row (modifier = Modifier.fillMaxWidth().padding(10.dp).padding(bottom = 20.dp, top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
        ){
        Text(text="Transactions", style = TextStyle(fontSize = 18.sp
            , fontWeight = FontWeight.SemiBold,), modifier =
        Modifier
            .padding(start=20.dp)
        ,
        )

        Icon(
//            painter = painterResource(R.drawable.payments_fill0_wght400_grad0_opsz24),
            imageVector = Icons.Filled.ArrowForward,
            contentDescription = "Item",
            tint = md_theme_light_primary,
            modifier = Modifier.size(24.dp)

        )

    }

}

