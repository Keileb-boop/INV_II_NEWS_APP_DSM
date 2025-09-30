package sv.edu.api.ui.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sv.edu.api.data.model.Article
import sv.udb.api.databinding.ItemNewsBinding

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

        //Para mostrar el titulo de la noticia
        holder.binding.tvTitle.text = article.title ?: "Sin título"

      // Mostrar la descripción de la noticia
        holder.binding.tvDescription.text = article.description ?: "Sin descripción"

       //Cargar la imagen de la noticia
        Glide.with(holder.itemView.context)
            .load(article.urlToImage)
            .into(holder.binding.ivNews)

        //Haciendo click en la noticia nos envia a la página web en el navegador del celular
        holder.binding.tvTitle.setOnClickListener {
            article.url?.let { link ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    fun updateData(newArticles: List<Article>) {
        articles.clear()
        articles.addAll(newArticles)
        notifyDataSetChanged()
    }
}
