package br.com.iwatasoftware.basickotlinapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.iwatasoftware.basickotlinapp.R
import br.com.iwatasoftware.basickotlinapp.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val countrys : List<Country>, private val context : Context) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    var onItemClick: ((Country) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countrys.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = countrys[position]
        holder.bindView(country)
    }

    inner class CountryViewHolder (itemView : View) :  RecyclerView.ViewHolder(itemView) {
        fun bindView (country : Country) {
            val icon = itemView.ic_country
            val name = itemView.tv_country

            itemView.setOnClickListener {
                onItemClick?.invoke(countrys[adapterPosition])
            }

            Log.i("info", "eu fui clicado")

            name.text = country.name
            icon.setImageResource(country.icon)
        }
    }
}