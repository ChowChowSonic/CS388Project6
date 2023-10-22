package com.codepath.articlesearch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DayAdapter(private val context: Context, private val days:List<Day>) : RecyclerView.Adapter<DayAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_best_seller_book, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = days.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = days[position]
        holder.bind(article)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val titleTextView = itemView.findViewById<TextView>(R.id.book_title)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.book_author)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: Day) {
            titleTextView.text = "Day No. " + article.id
            abstractTextView.text =
                article.caloriesEaten.toString() + " Calories Eaten, \n" +
                        article.steps + " Steps taken, \n" + article.sleepHrs + " Hours slept"
        }

        override fun onClick(v: View?) {
            // Get selected article
            val article = days[absoluteAdapterPosition]

            // Navigate to Details screen and pass selected article
            //val intent = Intent(context, DetailActivity::class.java)
            //intent.putExtra("ARTICLE_EXTRA", article)
            //context.startActivity(intent)
        }
    }
}