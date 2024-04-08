package nz.mikhailov.motiv

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ReleaseModule {

    @Provides
    fun provideAppCheckProviderFactory(): AppCheckProviderFactory =
        PlayIntegrityAppCheckProviderFactory.getInstance()
}