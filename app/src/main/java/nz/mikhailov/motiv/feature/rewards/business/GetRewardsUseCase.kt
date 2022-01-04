package nz.mikhailov.motiv.feature.rewards.business

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nz.mikhailov.motiv.feature.rewards.business.model.Reward

class GetRewardsUseCase {

    suspend operator fun invoke(): Flow<List<Reward>> = flow {
        emit(
            listOf(
                Reward.Code(1),
                Reward.Exercise(1),
                Reward.Study(2),
            )
        )
    }
}
