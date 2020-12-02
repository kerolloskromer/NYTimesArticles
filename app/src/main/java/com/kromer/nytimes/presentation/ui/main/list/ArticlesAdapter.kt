package com.kromer.nytimes.presentation.ui.main.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kromer.nytimes.R
import com.kromer.nytimes.domain.model.Article

class ArticlesAdapter(
    private var items: List<Article>,
    private var itemClick: (Article) -> Unit
) : RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(root)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, itemClick)
    }
}
