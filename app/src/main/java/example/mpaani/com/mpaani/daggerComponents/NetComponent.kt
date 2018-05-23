package example.mpaani.com.mpaani.daggerComponents

import dagger.Component
import example.mpaani.com.mpaani.ui.MainActivity
import example.mpaani.com.mpaani.daggerModules.NetModule
import example.mpaani.com.mpaani.networkModules.NetworkDataManager
import javax.inject.Singleton

@Singleton
@Component(modules = [NetModule::class])
interface NetComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(networkDataManager: NetworkDataManager)
}