package example.mpaani.com.mpaani.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import example.mpaani.com.mpaani.R
import example.mpaani.com.mpaani.databinding.PostDetailActivityBinding
import example.mpaani.com.mpaani.models.Post
import example.mpaani.com.mpaani.viewModels.PostDetailViewModel

class PostDetailActivity : AppCompatActivity() {

    lateinit var binding: PostDetailActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.post_detail_activity)
        val bundle = intent.getBundleExtra("Detail")
        val post = bundle.getSerializable("post") as Post
        binding.post = post

        val postViewModel = ViewModelProviders.of(this).get(PostDetailViewModel::class.java)
        observePostDetails(postViewModel, post)
    }

    private fun observePostDetails(viewModel: PostDetailViewModel, post: Post) {
        viewModel.getCommentList().observe(this, Observer {
            if (it == null) Toast.makeText(this, "Please check you connection", Toast.LENGTH_LONG).show()
            val comments = it?.filter { comment -> comment.postId == post.id }
            binding.count = comments?.size
        })
        viewModel.getUserList().observe(this, Observer {
            if (it == null) Toast.makeText(this, "Please check you connection", Toast.LENGTH_LONG).show()
            val user = it?.filter { user -> user.id == post.id }?.single()
            binding.user = user
        })
    }
}