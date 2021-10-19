package nz.mikhailov.motiv.feature.transactions.business.model

import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime.parse
import java.time.ZoneId.systemDefault

class TransactionKtTest {

    @Test
    fun `should convert to business object`() {
        val date = parse("2021-10-09T19:21:27").atZone(systemDefault())
        val dataObject = TransactionRecord(
            date = date.toInstant(),
            amount = 10,
            activity = "activity",
        )
        val result = dataObject.toBo()
        assertThat(result).isEqualTo(Transaction(
            amount = 10,
            date = "2021-10-09 19:21",
            activity = "activity",
        ))
    }
}