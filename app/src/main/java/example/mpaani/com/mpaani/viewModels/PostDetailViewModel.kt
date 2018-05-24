package example.mpaani.com.mpaani.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import example.mpaani.com.mpaani.models.Comment
import example.mpaani.com.mpaani.models.User
import example.mpaani.com.mpaani.networkModules.NetworkDataManager
import io.reactivex.Single

class PostDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val networkDataManager: NetworkDataManager

    init {
        networkDataManager = NetworkDataManager()
    }

    fun getUserList(): Single<ArrayList<User>> {
        return networkDataManager.getUsersData()
    }

    fun getCommentList(): Single<ArrayList<Comment>> {
        return networkDataManager.getCommentsData()
    }
}