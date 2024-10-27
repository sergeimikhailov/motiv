package nz.mikhailov.motiv.data.rewards

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class HardcodedRewardDataSource @Inject constructor() {

    val rewards: Flow<List<Reward>> = flowOf(
        listOf(
            Reward.Code(1),
            Reward.Exercise(1),
            Reward.Study(2),
            Reward.NoJunkFood(1),
        )
    )
}
