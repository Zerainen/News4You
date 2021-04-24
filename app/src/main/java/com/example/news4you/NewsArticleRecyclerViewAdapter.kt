package com.example.news4you

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.news4you.databinding.RecyclerViewArticleListItemBinding
import com.example.news4you.network.Article

class NewsArticleRecyclerViewAdapter(val clickListener: NewsArticleListener) : ListAdapter<Article, NewsArticleRecyclerViewAdapter.ViewHolder>(
    NewsArticleDiffCallback
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(item)
        }
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: RecyclerViewArticleListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.property = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerViewArticleListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    companion object NewsArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.source.id == newItem.source.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }


    class NewsArticleListener(val clickListener: (article: Article) -> Unit) {
        fun onClick(article: Article) = clickListener(article)
    }
}

