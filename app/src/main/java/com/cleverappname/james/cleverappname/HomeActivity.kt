package com.cleverappname.james.cleverappname

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.venue_item.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var FOURSQUARE_CLIENT_ID = "3O5RJUVSQSRYU3JEQNMAG1AM2CONYOAHB3SAMWSRRKUDANMP"
    private var FOURSQUARE_CLIENT_SECRET = "STMPSWLBHCRUACQTNXUR00RMHQIYMUBY14LGI51S3GWTDOOV"
    private var dataSet: ArrayList<ItemsItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        btnSearchAction.setOnClickListener {
            downloadData(editTextLocationInput.text.toString())
            editTextLocationInput.hideKeyboard()
        }
        mainContent.layoutManager = LinearLayoutManager(this)
        mainContent.adapter = VenuesAdapter(dataSet, this)
    }

    private fun downloadData(inputString: String) {
        doAsync {
            val tempUrl = ("https://api.foursquare.com/v2/venues/explore?client_id="
                    + FOURSQUARE_CLIENT_ID
                    + "&client_secret="
                    + FOURSQUARE_CLIENT_SECRET
                    + "&v=20180827&near="
                    + inputString)
            val s: String = URL("$tempUrl").readText()
            uiThread {
                val gson = Gson()
                val foursquareResults = gson.fromJson(s, Foursquarevenues::class.java)
                val venues = foursquareResults.response.groups?.get(0)?.items
                dataSet.clear()
                for (items in venues.orEmpty()) {
                    dataSet.add(items)
                }
                mainContent.adapter.notifyDataSetChanged()
            }
        }
    }

    /*  Extension to allow individual venues to be clicked and opening a detailed view of that venue
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
            uiThread {
                alert {  }
            }
        }
    }
    */

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    class VenuesAdapter(val items: ArrayList<ItemsItem>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(context).inflate(R.layout.venue_item, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.venueName?.text = items[position].venue.name
            holder.venueAddress?.text = items[position].venue.location.address
            holder.venueCategory?.text = items[position].venue.categories?.get(0)?.name
            holder.itemView.setOnClickListener(holder)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val venueName = view.venue_name
        val venueAddress = view.venue_address
        val venueCategory = view.venue_category

        override fun onClick(v: View?) {
            //call getVenueDetails(VENUE_ID) and update ui
        }
    }
}
