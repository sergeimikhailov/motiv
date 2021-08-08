package nz.mikhailov.motiv

import nz.mikhailov.motiv.feature.transactions.TransactionsFacade
import nz.mikhailov.motiv.feature.transactions.TransactionsFeature

interface Features {
    companion object {
        val transactions: TransactionsFeature
            get() = TransactionsFacade.newInstance()
    }
}
