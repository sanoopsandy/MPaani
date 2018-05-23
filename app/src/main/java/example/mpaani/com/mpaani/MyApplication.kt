package example.mpaani.com.mpaani

import android.app.Application
import example.mpaani.com.mpaani.daggerComponents.DaggerNetComponent
import example.mpaani.com.mpaani.daggerComponents.NetComponent
import example.mpaani.com.mpaani.daggerModules.NetModule
import example.mpaani.com.mpaani.utils.Constants

class MyApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic
        lateinit var netComponent: NetComponent
    }


    override fun onCreate() {
        super.onCreate()
        netComponent = DaggerNetComponent.builder()
                .netModule(NetModule(Constants.BASE_URL))
                .build()
    }
}