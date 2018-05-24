package example.mpaani.com.mpaani.ui

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import example.mpaani.com.mpaani.R
import example.mpaani.com.mpaani.databinding.PostDetailActivityBinding
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.models.User
import example.mpaani.com.mpaani.viewModels.PostDetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PostDetailActivity : AppCompatActivity() {

    lateinit var binding: PostDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.post_detail_activity)
        val bundle = intent.getBundleExtra("Detail")
        val post = bundle.getSerializable("post") as Post
        val postViewModel = ViewModelProviders.of(this).get(PostDetailViewModel::class.java)
        binding.progress.visibility = View.VISIBLE
        observeCommentDetails(postViewModel, post)
        observeUserDetails(postViewModel, post)
    }

    private fun observeUserDetails(viewModel: PostDetailViewModel, post: Post) {
        viewModel.getUserList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    val user = result.single { u -> u.id == post.userId }
                    binding.user = user
                    Picasso.get().load("https://api.adorable.io/avatars/285/${user.email}.png").into(binding.imgAvatar)
                    binding.progress.visibility = View.GONE
                }, { error ->
                    error.printStackTrace()
                    binding.progress.visibility = View.GONE
                })
    }

    private fun observeCommentDetails(viewModel: PostDetailViewModel, post: Post) {
        viewModel.getCommentList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ c ->
                    if (c == null) Toast.makeText(this, "Please check you connection", Toast.LENGTH_LONG).show()
                    val comments = c.filter { comment -> comment.postId == post.id }
                    binding.count = "Total Comments : ${comments.size}"
                    binding.post = post
                }, { error ->
                    error.printStackTrace()
                })
    }
}