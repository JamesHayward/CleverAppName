package com.cleverappname.james.cleverappname

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.google.gson.Gson
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
                val json = """
                    {
                        "id": "49b6e8d2f964a52016531fe3",
                        "name": "Russ & Daughters",
                        "location":
                            {
                                "address": "179 E Houston St",
                                "crossStreet": "btwn Allen & Orchard St",
                                "lat": 40.72286707707289,
                                "lng": -73.98829148466851,
                                "labeledLatLngs": [
                                    {
                                        "label": "display",
                                        "lat": 40.72286707707289,
                                        "lng": -73.98829148466851
                                    }
                                ],
                                "distance": 130,
                                "postalCode": "10002",
                                "cc": "US",
                                "city": "New York",
                                "state": "NY",
                                "country": "United States",
                                "formattedAddress": [
                                    "179 E Houston St (btwn Allen & Orchard St)",
                                    "New York, NY 10002",
                                    "United States"
                                ]
                            }
                    }
                """.trimIndent()
                val gson = Gson()
                val topic = gson.fromJson(json, Venue::class.java)
                dataSet.add(topic.name)
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

    class Venue(
            val id: String,
            val name: String,
            val formattedAddress: String,
            val lat: String,
            val lng: String
    )
}
