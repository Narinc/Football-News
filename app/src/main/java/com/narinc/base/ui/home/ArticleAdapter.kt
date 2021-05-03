package com.narinc.base.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.narinc.base.data.model.Item
import com.narinc.base.databinding.ItemArticleBinding
import com.narinc.base.util.loadImageOrDefault
import com.narinc.base.util.loadOrGone

class ArticleAdapter :
    PagingDataAdapter<Item, ArticleViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position) ?: return
        holder.bind(article)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Item> =
            object : DiffUtil.ItemCallback<Item>() {
                override fun areItemsTheSame(oldItem: Item, newItem: Item) =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Item, newItem: Item) =
                    oldItem.id == newItem.id
            }
    }
}


/**
 * View Holder for a [Item] RecyclerView list item.
 */
class ArticleViewHolder(private val binding: ItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(articleItem: Item) {
        binding.run {
            tvArticleSource.loadOrGone(articleItem.source.name)
            tvArticleTitle.loadOrGone(articleItem.title)
            ivArticle.loadImageOrDefault(articleItem.imgUrl)

            setOnClickListener(articleItem)
        }
    }

    private fun setOnClickListener(articleItem: Item) {
        binding.cardArticle.setOnClickListener{view ->
            navigateToDetail(articleItem, view)
        }
    }

    private fun navigateToDetail(item: Item, view: View) {

    }

    companion object {
        fun from(parent: ViewGroup): ArticleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemArticleBinding.inflate(layoutInflater, parent, false)
            return ArticleViewHolder(binding)
        }
    }
}