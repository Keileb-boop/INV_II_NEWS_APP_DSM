package sv.edu.api.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sv.edu.api.data.model.Article
import sv.udb.api.databinding.ItemNewsBinding
import sv.udb.api.databinding.ActivityMainBinding

class NewsAdapter(private val articles: MutableList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount() = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.tvTitle.text = article.title
        holder.binding.tvDescription.text = article.description

        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.binding.ivNews)
    }

    fun updateData(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}
