package com.mobot.starwas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var people: People? = null

        val call = APIClient().apiService().list()
        call.enqueue(object : Callback<People> {
            override fun onResponse(call: Call<People>, response: Response<People>) {
                response.body()?.let {
                    people = it
                    fillRecyclerView(people!!)
                }
            }

            override fun onFailure(call: Call<People>, t: Throwable) {
                Log.i("TAG", "error: " + t.message)
            }

        })

    }

    private fun fillRecyclerView(people: People) {
        val recyclerView = rvPeople
        recyclerView.adapter = PeopleListAdapter(people!!.results!!, this)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }


}