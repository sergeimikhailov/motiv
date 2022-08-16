package nz.mikhailov.motiv.feature.rewards.business

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import nz.mikhailov.motiv.feature.rewards.business.model.Reward

class  GetRewardsUseCase {

    operator fun invoke(): Flow<List<Reward>> = flowOf(
        listOf(
            Reward.Code(1),
            Reward.Exercise(1),
            Reward.Study(2),
            Reward.NoJunkFood(1),
        )
    )
}
