package br.com.iwatasoftware.basickotlinapp.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.iwatasoftware.basickotlinapp.R
import br.com.iwatasoftware.basickotlinapp.adapter.CountryAdapter
import br.com.iwatasoftware.basickotlinapp.model.Country
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewCountry = rv_country

        var countryAdapter = CountryAdapter(countrys(), this)
        countryAdapter.onItemClick = {country -> Toast.makeText(this, "O pais eh " + country.name, Toast.LENGTH_LONG).show()}
        recyclerViewCountry.adapter = countryAdapter
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewCountry.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerViewCountry.context, linearLayoutManager.orientation)
        recyclerViewCountry.addItemDecoration(dividerItemDecoration)

        val fabAddCountry : FloatingActionButton = fab_add_country
        fabAddCountry.setOnClickListener { startActivity(Intent(this, AddCountryActivity::class.java))}
    }

    private fun countrys () : List<Country> {
        return listOf(
            Country(R.mipmap.ic_brazil,"Brazil"),
            Country(R.mipmap.ic_brazil,"Alemanha"),
            Country(R.mipmap.ic_brazil,"China"),
            Country(R.mipmap.ic_brazil,"Itália"),
            Country(R.mipmap.ic_brazil,"Coreia do Sul"),
            Country(R.mipmap.ic_brazil,"Japão"))
    }
}
