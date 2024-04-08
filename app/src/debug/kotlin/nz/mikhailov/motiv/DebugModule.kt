package nz.mikhailov.motiv

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DebugModule {

    @Provides
    fun provideAppCheckProviderFactory() : AppCheckProviderFactory =
        DebugAppCheckProviderFactory.getInstance()
}
