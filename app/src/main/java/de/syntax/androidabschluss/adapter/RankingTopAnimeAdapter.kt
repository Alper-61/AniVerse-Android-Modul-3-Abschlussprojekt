package de.syntax.androidabschluss.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import de.syntax.androidabschluss.Utils.glideImageSet
import de.syntax.androidabschluss.data.models.AnimeData
import de.syntax.androidabschluss.R
import de.syntax.androidabschluss.ui.Utils.RankingFragmentDirections

class RankingTopAnimeAdapter(private val list: List<AnimeData>) :
    RecyclerView.Adapter<RankingTopAnimeAdapter.d>() {
    inner class d(v: View) : RecyclerView.ViewHolder(v) {
        val image: ImageView = v.findViewById(R.id.imageView2)
        val textTypeTitle: TextView = v.findViewById(R.id.textView2)
        val textReview: TextView = v.findViewById(R.id.textView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): d {
        val l = LayoutInflater.from(parent.context)
            .inflate(R.layout.design_home_top_anime_characters, parent, false)
        return d(l)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: d, position: Int) {
        val item = list[position]
        holder.apply {
            item.images?.jpg?.let { it.image_url?.let { it1 -> image.glideImageSet(it1) } }
            textTypeTitle.text =
                itemView.context.getString(R.string.type) + item.type + "\n" + itemView.context.getString(R.string.source) + (item.source)
            textReview.text = item.title

            itemView.setOnClickListener {view->
                val action = item.mal_id?.let {
                    RankingFragmentDirections.actionRankingFragmentToAnimeDetailFragment(
                        it
                    )
                }
                if (action != null) {
                    view.findNavController().navigate(action)
                }
            }
        }
    }
}