package example.mpaani.com.mpaani.networkModules

import example.mpaani.com.mpaani.models.Comment
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.models.User
import retrofit2.Call
import retrofit2.http.GET

interface IWebService {

    @GET("posts")
    fun getPosts(): Call<ArrayList<Post>>

    @GET("users")
    fun getUsers(): Call<ArrayList<User>>

    @GET("comments")
    fun getComments(): Call<ArrayList<Comment>>

}