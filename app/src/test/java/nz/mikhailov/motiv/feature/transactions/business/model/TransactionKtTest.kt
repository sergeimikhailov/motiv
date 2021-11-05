package nz.mikhailov.motiv.feature.transactions.business.model

import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime.parse
import java.time.ZoneId.systemDefault
import java.time.format.DateTimeFormatter

class TransactionKtTest {

    @Test
    fun `should convert to business object`() {
        val testFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm")
            .withZone(systemDefault())
        val date = parse("2021-10-09T19:21:27").atZone(systemDefault())
        val dataObject = TransactionRecord(
            date = date.toInstant(),
            amount = 10,
            activity = "activity",
            balance = 25,
        )
        val result = dataObject.toBo(testFormatter)
        assertThat(result).isEqualTo(Transaction(
            amount = 10,
            date = "2021-10-09 19:21",
            activity = "activity",
        ))
    }
}