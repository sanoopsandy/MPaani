package example.mpaani.com.mpaani.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.networkModules.NetworkDataManager
import io.reactivex.Single

class PostListViewModel(application: Application) : AndroidViewModel(application) {

    private val networkDataManager: NetworkDataManager = NetworkDataManager()

    fun getPostInfoList(): Single<ArrayList<Post>> {
        return networkDataManager.getPostsInfo()
    }
}