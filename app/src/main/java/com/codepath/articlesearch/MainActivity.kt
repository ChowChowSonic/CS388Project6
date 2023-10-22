package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val days = ArrayList<Day>()
    private lateinit var articlelist:ArticleListFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articlelist = ArticleListFragment()
        days.add(Day(1,2,3,4,5))
        days.add(Day(2,4,3,4,5))
        days.add(Day(3,6,9,12,13))
        days.add(Day(4,5,6,7,8))
        days.add(Day(5,10,15,20,30))
        days.add(Day(6,10,15,20,30))
        days.add(Day(7,10,15,20,30))
        days.add(Day(8,10,15,20,30))

        articlelist.days = days
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener{

            if(it.itemId == R.id.nav_articles) replaceFragment(articlelist)
            else replaceFragment(BestSellerBooksFragment(days))
            true

        }
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.nav_articles
    }
    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, articleListFragment)
        fragmentTransaction.commit()
    }
}