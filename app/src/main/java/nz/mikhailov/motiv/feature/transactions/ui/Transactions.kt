package nz.mikhailov.motiv.feature.transactions.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nz.mikhailov.motiv.feature.transactions.ui.model.Transaction
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun Transactions(transactions: List<Transaction>) {
    val dateFormat = remember { SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()) }
    LazyColumn(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(transactions) { transaction ->
            Text(text = "${dateFormat.format(transaction.date)} - $${transaction.amount}")
        }
    }
}