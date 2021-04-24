package com.example.news4you

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.news4you.network.Article
import com.example.news4you.overview.LoadingStatus

@BindingAdapter("loadingStatus")
fun bindLoadingStatusToImageView(statusImageView: ImageView, status: LoadingStatus) {
    when(status) {
        LoadingStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        LoadingStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        LoadingStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

@BindingAdapter("articles")
fun bindArticlesToRecyclerView(recyclerView: RecyclerView, articles: List<Article>?) {
    val adapter = recyclerView.adapter as NewsArticleRecyclerViewAdapter
    adapter.submitList(articles)
}

@BindingAdapter("imageUrl")
fun bindImageToRecyclerViewItem(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri =
            imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}