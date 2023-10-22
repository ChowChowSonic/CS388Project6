package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "ArticleListFragment"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val ARTICLE_SEARCH_URL =
    "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=${SEARCH_API_KEY}"
class ArticleListFragment : Fragment() {
    lateinit var layoutview: View
    var days: ArrayList<Day> = ArrayList()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Change this statement to store the view in a variable instead of a return statement
        layoutview = inflater.inflate(R.layout.fragment_article_list, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        var avgcals = 12
        var avgsteps = 26
        var avgsleepHrs = 8

        for(d in days){
                avgcals+=d.caloriesEaten
                avgsteps+=d.steps
                avgsleepHrs+=d.sleepHrs
        }
        layoutview.findViewById<TextView>(R.id.summary).text = avgcals.toString() + " total Calories Eaten, \n" +
                avgsteps + " total Steps taken, \n" + avgsleepHrs + " total Hours slept"
        // Update the return statement to return the inflated view from above
        return layoutview
    }

    fun update(){
        var avgcals = 0
        var avgsteps = 0
        var avgsleepHrs = 0
        for(d in days){
            avgcals+=d.caloriesEaten
            avgsteps+=d.steps
            avgsleepHrs+=d.sleepHrs
        }
        layoutview.findViewById<TextView>(R.id.summary).text = avgcals.toString() + " total Calories Eaten, \n" +
                avgsteps + " total Steps taken, \n" + avgsleepHrs + " total Hours slept"

    }

    private fun fetchArticles() {
        val client = AsyncHttpClient()
        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        SearchNewsResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.response?.docs?.let { list ->
//                        days.addAll(list)
//                        articleAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })
    }

}