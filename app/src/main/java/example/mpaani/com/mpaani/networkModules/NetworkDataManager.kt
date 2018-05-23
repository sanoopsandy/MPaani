package example.mpaani.com.mpaani.networkModules

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import example.mpaani.com.mpaani.MyApplication
import example.mpaani.com.mpaani.models.Comment
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NetworkDataManager {

    @Inject
    lateinit var iWebService: IWebService

    fun getPostsData(): LiveData<ArrayList<Post>> {
        MyApplication.netComponent.inject(this)
        val data = MutableLiveData<ArrayList<Post>>()
        iWebService.getPosts().enqueue(object : Callback<ArrayList<Post>> {
            override fun onFailure(call: Call<ArrayList<Post>>?, t: Throwable?) {
                t?.printStackTrace()
                data.value = null
            }

            override fun onResponse(call: Call<ArrayList<Post>>?, response: Response<ArrayList<Post>>?) {
                val musicList = response?.body()
                data.value = musicList
            }

        })
        return data
    }

    fun getUsersData(): LiveData<ArrayList<User>> {
        MyApplication.netComponent.inject(this)
        val data = MutableLiveData<ArrayList<User>>()
        iWebService.getUsers().enqueue(object : Callback<ArrayList<User>> {
            override fun onFailure(call: Call<ArrayList<User>>?, t: Throwable?) {
                t?.printStackTrace()
                data.value = null
            }

            override fun onResponse(call: Call<ArrayList<User>>?, response: Response<ArrayList<User>>?) {
                val musicList = response?.body()
                data.value = musicList
            }

        })
        return data
    }

    fun getCommentsData(): LiveData<ArrayList<Comment>> {
        MyApplication.netComponent.inject(this)
        val data = MutableLiveData<ArrayList<Comment>>()
        iWebService.getComments().enqueue(object : Callback<ArrayList<Comment>> {
            override fun onFailure(call: Call<ArrayList<Comment>>?, t: Throwable?) {
                t?.printStackTrace()
                data.value = null
            }

            override fun onResponse(call: Call<ArrayList<Comment>>?, response: Response<ArrayList<Comment>>?) {
                val musicList = response?.body()
                data.value = musicList
            }

        })
        return data
    }

}