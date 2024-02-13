@file:OptIn(ExperimentalMaterial3Api::class)

package ug.ledge.pangy.screens.transactions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ug.ledge.pangy.dataclasses.Transactions

@Composable
fun TransactionDetail(transaction: Transactions){



}

@Composable
fun AppBar(title: String, icon: ImageVector, clickAction: () -> Unit){
    TopAppBar(

        title = { Text(title) },
        navigationIcon = { Icon(imageVector = icon,
            contentDescription = title , Modifier.padding(horizontal = 12.dp)
                .clickable { clickAction.invoke() }) } )
}