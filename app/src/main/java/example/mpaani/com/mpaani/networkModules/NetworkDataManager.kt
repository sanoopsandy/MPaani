package example.mpaani.com.mpaani.networkModules

import example.mpaani.com.mpaani.MyApplication
import example.mpaani.com.mpaani.models.Comment
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.models.User
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataManager {

    @Inject
    lateinit var iWebService: IWebService

    init {
        MyApplication.netComponent.inject(this)
    }

    fun getPostsInfo(): Single<ArrayList<Post>> {
        return iWebService.getPostInfo()
    }

    fun getUsersData(): Single<ArrayList<User>> {
        return iWebService.getUsers()
    }

    fun getCommentsData(): Single<ArrayList<Comment>> {
        return iWebService.getComments()
    }

}