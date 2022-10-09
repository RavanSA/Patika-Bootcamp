package android.project.financialnews.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.project.financialnews.R
import android.project.financialnews.data.remote.dto.Article
import android.project.financialnews.databinding.LayoutNewsListItemBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide

class FinancialNewsRecyclerAdapter(
    private val context: Context,
    private var articles: List<Article>,
    private val listener: FinancialNewsListener
) : RecyclerView.Adapter<FinancialNewsRecyclerAdapter.FinancialNewsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FinancialNewsRecyclerViewHolder {
        val newsListLayout: LayoutNewsListItemBinding = LayoutNewsListItemBinding.inflate(LayoutInflater
            .from(context), parent, false)

        return FinancialNewsRecyclerViewHolder(newsListLayout)
    }

    override fun onBindViewHolder(holder: FinancialNewsRecyclerViewHolder, position: Int) {
        val articleList = articles[position]
        holder.bind(articleList, listener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return  oldItem.content == newItem.content
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    inner class FinancialNewsRecyclerViewHolder(
        private val binding: LayoutNewsListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(articleList: Article, listener: FinancialNewsListener) {
            binding.title.text = articleList.title
            binding.description.text = articleList.description
            binding.author.text = articleList.author
            binding.date.text = articleList.publishedAt

            Glide.with(itemView.context).load(articleList.urlToImage)
                 .into(binding.image)

            itemView.setOnClickListener {
                listener.onClicked(articleList)
            }
        }
    }


}

interface FinancialNewsListener {
    fun onClicked(news: Article)
}