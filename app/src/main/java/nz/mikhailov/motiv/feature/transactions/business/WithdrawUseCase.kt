package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.mikhailov.motiv.feature.transactions.data.TransactionRepository
import nz.mikhailov.motiv.feature.transactions.data.model.TransactionRecord
import java.time.Instant

class WithdrawUseCase(
    private val repository: TransactionRepository = TransactionRepository(),
) {

    suspend operator fun invoke(amount: Int) = withContext(Dispatchers.IO) {
        if (amount <= 0) {
            return@withContext
        }
        val balance = repository.latestTransaction()?.balance ?: 0
        repository.insert(
            TransactionRecord(
                date = Instant.now(),
                amount = -amount,
                balance = balance - amount,
            )
        )
    }
}
