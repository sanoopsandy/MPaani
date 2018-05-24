package example.mpaani.com.mpaani.utils

import android.content.Context
import android.databinding.BindingAdapter
import android.support.annotation.LayoutRes
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso
import example.mpaani.com.mpaani.R
import example.mpaani.com.mpaani.adapter.BaseRecyclerAdapter


class BindingUtil {

    companion object {

        @JvmStatic
        @BindingAdapter(value = arrayOf("itemLayout", "items", "context"))
        fun configureAdapter(recyclerView: RecyclerView, @LayoutRes layoutId: Int, items: List<*>, context: Context) {
            try {
                val mLayoutManager = LinearLayoutManager(context)
                recyclerView.layoutManager = mLayoutManager
                val adapter = recyclerView.adapter as BaseRecyclerAdapter
                adapter.setLayoutId(layoutId)
                adapter.setItems(items)
                adapter.setContext(context)
                recyclerView.adapter = adapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {
            if (imageUrl.isNotEmpty()) {
                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_image)
                        .into(view)
            }
        }
    }
}