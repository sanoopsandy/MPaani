package example.mpaani.com.mpaani.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import example.mpaani.com.mpaani.models.Comment
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.models.User
import example.mpaani.com.mpaani.networkModules.NetworkDataManager

class PostDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val networkDataManager: NetworkDataManager

    init {
        networkDataManager = NetworkDataManager()
    }

    /*
    * Expose livedata to view
    * */
    fun getUserList(): LiveData<ArrayList<User>> {
        return networkDataManager.getUsersData()
    }

    fun getCommentList(): LiveData<ArrayList<Comment>> {
        return networkDataManager.getCommentsData()
    }
}