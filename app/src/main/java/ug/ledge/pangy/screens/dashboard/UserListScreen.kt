package ug.ledge.pangy.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeNodeLifecycleCallback
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ug.ledge.pangy.R
import ug.ledge.pangy.dataclasses.User

import ug.ledge.pangy.screens.dashboard.viewModels.UsersViewModel
import ug.ledge.pangy.ui.theme.PANGYTheme


@Composable
fun UserList(navigationCallback: (String)->Unit) {

    val viewModel: UsersViewModel = viewModel()
    val usersList = viewModel.UsersState.value

    LazyColumn(contentPadding = PaddingValues(5.dp)) {

        items(usersList!!) { user ->

            DetailCard(user = user, imageSize = 72.dp, navigationCallback)


        }
    }
}

@Composable
fun DetailCard(user: User?, imageSize: Dp, navigationCallback: (String)->Unit) {
    Card(
        modifier = Modifier
            .padding(10.dp).clickable { navigationCallback(user?.username!!) },
//                .border(BorderStroke(color = Color.Blue, width = 2.dp), shape = CircleShape),
        shape = RoundedCornerShape(10.dp),

        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .border(BorderStroke(2.dp, color = Color.Green), shape = CircleShape) ,
                shape = CircleShape

            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(user?.profile_pic)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(imageSize)
                        .align(Alignment.CenterHorizontally),


                    )
            }
            CardDetails(user = user)

        }


    }

}
@Composable
fun CardDetails(user: User?){

    Column(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
//        ,
    ) {
        Text(text=user?.username!!,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize=16.sp)
        )
        Text(text = user.uType!!)
    }

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PANGYTheme {
        UserList({})
    }
}