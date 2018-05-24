package example.mpaani.com.mpaani.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import example.mpaani.com.mpaani.R
import example.mpaani.com.mpaani.adapter.BaseRecyclerAdapter
import example.mpaani.com.mpaani.databinding.ActivityMainBinding
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.viewModels.PostDetailViewModel
import example.mpaani.com.mpaani.viewModels.PostListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BaseRecyclerAdapter.CustomClickListener {

    lateinit var binding: ActivityMainBinding
    lateinit var postList: ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.context = this
        val postViewModel = ViewModelProviders.of(this).get(PostListViewModel::class.java)
        postList = ArrayList()

        binding.items = postList
        val baseAdapter = BaseRecyclerAdapter()
        rvPostList.adapter = baseAdapter
        baseAdapter.clickListener = this

        binding.progress.visibility = View.VISIBLE
        observePostInfoList(postViewModel)
    }

    private fun observePostInfoList(viewModel: PostListViewModel) {
        viewModel.getPostInfoList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    if (result != null) {
                        postList = result
                        binding.progress.visibility = View.GONE
                        observeUserDetails(viewModel)
                    } else {
                        Toast.makeText(this, "Please check you connection", Toast.LENGTH_LONG).show()
                    }
                }, { error ->
                    error.printStackTrace()
                })
    }

    private fun observeUserDetails(viewModel: PostListViewModel) {
        viewModel.getUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    postList.map { p ->
                        val email = result.single { u -> u.id == p.userId}.email
                        p.avatarUrl = "https://api.adorable.io/avatars/285/${email}.png"
                    }
                    binding.items = postList
                }, { error ->
                    error.printStackTrace()
                })
    }

    override fun onCustomClick(view: View, position: Int) {
        val bundle = Bundle()
        bundle.putSerializable("post", postList[position])
        val intent = Intent(this, PostDetailActivity::class.java)
        intent.putExtra("Detail", bundle)
        startActivity(intent)
    }
}
