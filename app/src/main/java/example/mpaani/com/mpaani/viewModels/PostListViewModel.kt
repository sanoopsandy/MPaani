package example.mpaani.com.mpaani.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.networkModules.NetworkDataManager

class PostListViewModel(application: Application) : AndroidViewModel(application) {

    private val networkDataManager: NetworkDataManager

    init {
        networkDataManager = NetworkDataManager()
    }

    /*
    * Expose livedata to view
    * */
    fun getPostList(): LiveData<ArrayList<Post>> {
        return networkDataManager.getPostsData()
    }

}