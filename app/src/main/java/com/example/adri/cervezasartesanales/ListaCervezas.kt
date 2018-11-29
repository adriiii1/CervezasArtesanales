package com.example.adri.cervezasartesanales

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.database.*
import com.google.gson.Gson
import android.widget.Toast


class ListaCervezas : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    var listCervezas: ArrayList<Cervezas> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_cervezas)

        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("Cervezas")
        val db = dbReference.child("cervezas")
        val dbListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val gson = Gson()
                listCervezas.clear()
                var num = dataSnapshot.childrenCount
                for (obj in dataSnapshot.children) {
                    var cerveza = obj.value
                    Log.d("cerveza", dataSnapshot.toString())
                    var reg: Cervezas = gson.fromJson(cerveza.toString(), Cervezas::class.java)
                    listCervezas.add(reg)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                throw databaseError.toException()
            }
        }
        dbReference.child("cervezas").addValueEventListener(dbListener)

        var cerveza = Cervezas(0, "As", "sad", "33cl", 2.0)
        listCervezas.add(cerveza)

        viewManager = LinearLayoutManager(this)
        viewAdapter = miAdapter(listCervezas)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
