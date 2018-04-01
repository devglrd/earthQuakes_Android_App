package ninja.irvyne.earthquakes

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ninja.irvyne.earthquakes.api.EarthquakeService
import ninja.irvyne.earthquakes.api.model.EarthquakeData
import org.jetbrains.anko.longToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListActivity : AppCompatActivity() {

    private lateinit var mService: EarthquakeService
    private var mMessage: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val listView = findViewById<ListView>(R.id.listItem)
        //val redColor = Color.parseColor("#FF0000")
        //listView.setBackgroundColor(redColor);

        val retrofit = Retrofit.Builder()
                .baseUrl("https://earthquake.usgs.gov/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        mService = retrofit.create<EarthquakeService>(EarthquakeService::class.java)

        print(mService)

        val request = when (mMessage) {
            "All" -> mService.listSignificantEarthquakes()
            "M1.0+" -> mService.listM4Earthquakes()
            else -> mService.listSignificantEarthquakes()
        }


        request.enqueue(object : Callback<EarthquakeData> {
            override fun onFailure(call: Call<EarthquakeData>?, t: Throwable?) {
                Log.e(TAG, "An error occurred with listSignificantEarthquakes(), error: $t")
                longToast("Oups, an error occurred 🤟")
            }

            override fun onResponse(call: Call<EarthquakeData>?, response: Response<EarthquakeData>?) {
                Log.d(TAG, "Success, ${response?.body()}")
                longToast("Success 🍾")

            }
        })


        listView.adapter = MyCustomAdapter(this) // this needs to bbe my custom adapter
    }

    companion object {
        private const val TAG = "ListActivity"
    }

    private class MyCustomAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context

        init{
            mContext = context
        }

        override fun getCount(): Int {
            return 5
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        //resposible for rendergin out each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val textView = TextView(mContext)
            textView.text = "HERE is my ROW for my ListView"
            return textView
        }
    }

    fun fetchJson(){

    }

}
