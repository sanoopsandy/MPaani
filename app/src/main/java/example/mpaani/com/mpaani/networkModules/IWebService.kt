package example.mpaani.com.mpaani.networkModules

import example.mpaani.com.mpaani.models.Comment
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.models.User
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface IWebService {


    @GET("posts")
    fun getPostInfo(): Single<ArrayList<Post>>

    @GET("users")
    fun getUsers(): Single<ArrayList<User>>

    @GET("comments")
    fun getComments(): Single<ArrayList<Comment>>

}