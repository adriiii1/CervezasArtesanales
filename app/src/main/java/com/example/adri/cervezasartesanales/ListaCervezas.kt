package com.example.adri.cervezasartesanales

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.Gson
import java.io.IOException
import java.net.URL


class ListaCervezas : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var listCervezas: ArrayList<Cervezas> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cervezas)

        URLJsonObjeto()

        viewManager = LinearLayoutManager(this)
        viewAdapter = miAdapter(listCervezas)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun URLJsonObjeto() {
        val gson = Gson()
        try {
            val json = leerUrl("http://iesayala.ddns.net/digAdrian/jason.php")
            val cervezass = gson.fromJson(json, cervezasArray::class.java)
            for (item in cervezass.cervezas!!.iterator()) {
                listCervezas.add(item)
            }
        } catch (e: Exception) {
            Log.d("RESULTADO", "error")
        }
    }

    private fun leerUrl(urlString:String): String{
        val response = try {
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }
        return response
    }
}
