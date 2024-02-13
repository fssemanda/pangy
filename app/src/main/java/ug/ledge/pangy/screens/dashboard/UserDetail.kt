package ug.ledge.pangy.screens.dashboard

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import ug.ledge.pangy.R
import ug.ledge.pangy.Transactions.TransactionsViewModel
import ug.ledge.pangy.dataclasses.JustMethods
import ug.ledge.pangy.dataclasses.PropertyImages
import ug.ledge.pangy.dataclasses.Transactions
import ug.ledge.pangy.dataclasses.User
import ug.ledge.pangy.screens.dashboard.viewModels.PropertyViewModel
import ug.ledge.pangy.screens.dashboard.viewModels.UsersViewModel
import ug.ledge.pangy.screens.propertyListing.PropertyImagesVM
import ug.ledge.pangy.ui.theme.md_theme_light_primary
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

class UserDetail {
}

@Composable
fun UserDetailScreen(user: User?) {
    val scrollState = rememberLazyListState()
//    val scrollState = rememberScrollState()
    val offset = min(
        1f, 1 - (scrollState.firstVisibleItemScrollOffset / 600f
                + scrollState.firstVisibleItemIndex)
    )
    val imageSize by animateDpAsState(targetValue = max(24.dp, 72.dp * offset), label = "")

    Surface(

        color = MaterialTheme.colorScheme.background
    )
    {
        Column {
          Row {
                Column {

                    Surface(shadowElevation = 10.dp) {
                        Row(
                            modifier = Modifier
//                        .padding(10.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Welcome",
                                    modifier = Modifier.padding(10.dp),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                )
                                Text(
                                    text = "${user?.firstName} ${user?.lastName}"
                                        ?: "Name not retrieved",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .align(Alignment.CenterHorizontally)

                                )
                            }

                            Card(
                                modifier = Modifier.padding(16.dp),
                                shape = CircleShape,
                                border = BorderStroke(width = 2.dp, color = md_theme_light_primary)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(user?.profile_pic)
                                        .transformations(CircleCropTransformation()).crossfade(true)
                                        .build(),

//                  placeholder = painterResource(R.drawable.avator),
                                    contentDescription = "", contentScale = ContentScale.Crop,

                                    modifier = Modifier
                                        .size(imageSize)

                                )
                            }


                        }

                    }
                    val propertyImagesViewModel: PropertyImagesVM = viewModel()
                    var isLoading by remember { mutableStateOf(true) }
                    val loading = propertyImagesViewModel.isLoading.value
                    Log.d("Loading Value in Composable", "$loading")
                    Box(modifier = Modifier.fillMaxSize()){
                    val imageList = propertyImagesViewModel.rememberedPropertyState.value
                    LazyColumn(state = scrollState,modifier = Modifier
//                        .fillMaxSize()
                        .padding(top = 10.dp)
                    ) {
                        items(imageList) {
                            ImageCard(
                                property = it,
                                contentDescription = it.description!!,
                                title = it.caption!!
                            )
                        }
                    }
//                        CirCularLoadingSpinner(isDisplayed = loading)

                }
                }
            }
//            Spacer(modifier = Modifier.height(100.dp))
//            val uname = remember{ mutableStateOf(user?.username!!) }
//            UserTransactionListing(user)
        }
    }
}


@Composable
fun ImageCard(
    property: PropertyImages,
    contentDescription: String,
    title: String,
//    imageSize: Dp,
    modifier: Modifier = Modifier,

) {
    val propVm:PropertyImagesVM = viewModel()
    val loading = propVm.isLoading.value
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    )
    {
        Box(
            modifier = Modifier
                .height(150.dp)
                .fillMaxWidth(0.5f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(property?.propertyImage)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = property?.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
//                    .align(Alignment.CenterHorizontally),


            )
                        CirCularLoadingSpinner(isDisplayed = loading)
//

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(title, style = TextStyle(color = Color.Gray, fontSize = 20.sp))
//                Text(contentDescription, style = TextStyle(color = Color.Gray, fontSize = 20.sp))
            }
        }

    }

}
@Composable
fun CirCularLoadingSpinner(isDisplayed:Boolean){
    
    if (isDisplayed){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
            , verticalAlignment = Alignment.CenterVertically
        ){ CircularProgressIndicator()}
    }
    
}

@Composable
//fun UserTransactionListing(userId: MutableState<String>){
fun UserTransactionListing(user: User?){
    Row {
        val userVM:UsersViewModel = viewModel()

        val transactionsVM: TransactionsViewModel = viewModel()
//        transactionsVM.setUserId(user?.username!!)
        val transactionListing = transactionsVM.userTransactionState.value
        val myState = remember { mutableStateOf(transactionsVM.myState ?: "Default Value") }

        myState.value=user?.username.toString()

        Log.d("Francis1", "${myState.value}")
        LazyColumn(/*state = scrollState,*/modifier = Modifier
//                    .fillMaxSize()
            .padding(top = 10.dp)
        ) {
            items(transactionListing) {
                TransactionDataItem(it)
            }

        }
    }
}

@Composable
fun TransactionDataItem(transaction:Transactions){
    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.CenterStart
            ) {
                Card(modifier = Modifier

//                    .padding(5.dp)
                    .background(color = Color.White,
//                        shape = RoundedCornerShape(10.dp)
                        )
//                , shape = RoundedCornerShape(10.dp)
                )
                     {
                    Text(text = transactionTypeSelector(transaction.transactionType!!),
                        style = TextStyle(fontSize = 22.sp,
                        fontWeight = FontWeight.Bold)
                    , modifier = Modifier.padding(20.dp))
                }
//            Text(text="${transaction.receiver_firstName!!} ${transaction.receiver_lastName!!} ")
            }
            Box( modifier = Modifier
                .fillMaxSize(),contentAlignment = Alignment.TopEnd) {

                val (date, time) = parseDateTime(transaction.transactionTime!!,)
                    Text(
                        text = date,
                        style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 13.sp)
                    )

            }
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(start = 60.dp),

                contentAlignment = Alignment.CenterStart) {

//
//                    Text(
            Text(text="${transaction.receiver_firstName!!} ${transaction.receiver_lastName!!} ",
                style=TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
                )
//                    val (date, time) = parseDateTime(transaction.transactionTime!!,)
//                    Text(
//                        text = date,
//                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 13.sp)
//                    )

//                Text(text=transaction.transactionTime!!,
//                    style=TextStyle(fontWeight = FontWeight.Bold, fontSize = 13.sp))
            }
            Box( modifier = Modifier
                .fillMaxSize(),contentAlignment = Alignment.CenterEnd) {
                Text(text="UGX. ${JustMethods.myFormatter.format(transaction.amount)}",
                    style=TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp))
            }
            Box( modifier = Modifier
                .fillMaxSize()
                .padding(start = 60.dp, top = 40.dp),
                contentAlignment = Alignment.CenterStart) {
                Text(text=transaction.transactionType.toString(),
                    style=TextStyle(fontWeight = FontWeight.Light, fontSize = 16.sp))
            }


        }

    }

}

fun parseDateTime(dateTimeStr: String): Pair<String, String> {
    val dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME)
    val date = dateTime.format(DateTimeFormatter.ofPattern("dd-MMMM-YY"))
    val time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    return date to time
}

fun transactionTypeSelector(TransactionType:String):String{
    var myText:String
    if (TransactionType == "Deposit")
        return "D"
    else if (TransactionType == "Rent Fulfillment")
        return "R"
    else if (TransactionType == "Credit Transfer")
        return "C"
    else if (TransactionType == "Savings")
        return "S"
    else if (TransactionType == "Management Fees")
        return "F"
    else if (TransactionType == "Interest")
        return "I"
    else if (TransactionType == "Withdraw")
        return "W"
    else if (TransactionType == "Withdraw Fee")
        return "F"
    else
        return "O"

}


//@Composable
//fun TransactionTYpeFormatter(transactionType: String){
//    Box(
//        contentAlignment = Alignment.TopEnd
//    ){
//
//    }
//
//}


//@Preview(showBackground = true)
//@Composable
//fun DetailScreenPreview() {
//    PANGYTheme {
////        UserDetailScreen({})
//    }
//}