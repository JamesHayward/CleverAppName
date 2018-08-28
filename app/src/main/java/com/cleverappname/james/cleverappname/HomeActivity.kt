package com.cleverappname.james.cleverappname

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.google.gson.*
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.venue_item.view.*
import org.jetbrains.anko.*
import java.net.URL
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var FOURSQUARE_CLIENT_ID = "3O5RJUVSQSRYU3JEQNMAG1AM2CONYOAHB3SAMWSRRKUDANMP"
    private var FOURSQUARE_CLIENT_SECRET = "STMPSWLBHCRUACQTNXUR00RMHQIYMUBY14LGI51S3GWTDOOV"
    private var dataSet: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        btnSearchAction.setOnClickListener { downloadData(editTextLocationInput.text.toString()) }
//        mainContent.setOnClickListener { getVenueDetails("4b7c1a5ef964a520137d2fe3") }
        mainContent.layoutManager = LinearLayoutManager(this)
        mainContent.adapter = VenuesAdapter(dataSet, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun downloadData(inputString: String) {
        doAsync {
            val tempUrl = ("https://api.foursquare.com/v2/venues/explore?client_id="
                    + FOURSQUARE_CLIENT_ID
                    + "&client_secret="
                    + FOURSQUARE_CLIENT_SECRET
                    + "&v=20180827&near="
                    + inputString)
            val s: String = URL("$tempUrl").readText()
            //TODO: handle this data and display it in a pretty way
            uiThread {
                val gson = Gson()
                val venues = gson.fromJson(s, Foursquarevenues::class.java)
                dataSet.clear()
                dataSet.add(venues.response.groups?.get(0)?.items?.get(0)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(1)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(2)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(3)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(4)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(5)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(6)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(7)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(8)?.venue?.name.toString())
                dataSet.add(venues.response.groups?.get(0)?.items?.get(9)?.venue?.name.toString())
                mainContent.adapter.notifyDataSetChanged()
            }
        }
    }

    fun getVenueDetails(inputString: String) {
        doAsync {
            val tempUrl = ("https://api.foursquare.com/v2/venues/"
                    + inputString
                    + "?client_id="
                    + FOURSQUARE_CLIENT_ID
                    + "&client_secret="
                    + FOURSQUARE_CLIENT_SECRET
                    + "&v=20180827")
            val s: String = URL("$tempUrl").readText()
            //TODO: handle this data and display it in a pretty way
            uiThread {
                //                mainContent.text = s
            }
        }
    }

    class VenuesAdapter(val items: ArrayList<String>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.venue_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.venueName?.text = items[position]
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val venueName = view.venue_name
    }
}
