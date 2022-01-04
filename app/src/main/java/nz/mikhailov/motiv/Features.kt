package nz.mikhailov.motiv

import nz.mikhailov.motiv.feature.rewards.RewardsFacade
import nz.mikhailov.motiv.feature.rewards.RewardsFeature
import nz.mikhailov.motiv.feature.settings.SettingsFacade
import nz.mikhailov.motiv.feature.settings.SettingsFeature
import nz.mikhailov.motiv.feature.transactions.TransactionsFacade
import nz.mikhailov.motiv.feature.transactions.TransactionsFeature

interface Features {
    companion object {
        val rewards: RewardsFeature
            get() = RewardsFacade.newInstance()
        val transactions: TransactionsFeature
            get() = TransactionsFacade.newInstance()
        val settings: SettingsFeature
            get() = SettingsFacade.newInstance()
    }
}
