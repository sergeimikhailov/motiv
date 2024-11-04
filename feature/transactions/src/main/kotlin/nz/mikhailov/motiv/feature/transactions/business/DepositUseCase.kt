package nz.mikhailov.motiv.feature.transactions.business

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.mikhailov.motiv.data.transactions.TransactionRecord
import nz.mikhailov.motiv.data.transactions.TransactionRepository
import java.time.Instant
import javax.inject.Inject

class DepositUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {

    suspend operator fun invoke(amount: Int, activity: String) = withContext(Dispatchers.IO) {
        val balance = repository.latestTransaction()?.balance ?: 0
        repository.insert(
            TransactionRecord(
                date = Instant.now(),
                amount = amount,
                activity = activity,
                balance = balance + amount,
            )
        )
    }
}
