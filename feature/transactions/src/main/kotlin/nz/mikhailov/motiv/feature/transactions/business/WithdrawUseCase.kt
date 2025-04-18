package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.mikhailov.motiv.data.transactions.TransactionRecord
import nz.mikhailov.motiv.data.transactions.TransactionRepository
import java.time.Instant
import javax.inject.Inject

class WithdrawUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {

    suspend operator fun invoke(amount: Double) = withContext(Dispatchers.IO) {
        if (amount <= 0) {
            return@withContext
        }
        val amountCents = (amount * 100).toInt()
        val balance = repository.latestTransaction()?.balance ?: 0
        repository.insert(
            TransactionRecord(
                date = Instant.now(),
                amount = -amountCents,
                balance = balance - amountCents,
            )
        )
    }
}
