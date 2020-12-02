package com.kromer.nytimes.presentation.ui.main.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kromer.nytimes.databinding.ItemArticleBinding
import com.kromer.nytimes.domain.model.Article

class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = ItemArticleBinding.bind(itemView)

    fun bind(item: Article, itemClick: (Article) -> Unit) {
        val image: String = if (item.media.isNotEmpty()) {
            item.media[0].mediaMetadata[0].url
        } else {
            ""
        }
        binding.ivPhoto.load(image)
        binding.tvTitle.text = item.title
        binding.tvDate.text = item.publishedDate
        itemView.setOnClickListener {
            itemClick.invoke(item)
        }
    }
}