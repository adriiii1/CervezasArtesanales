package com.example.adri.cervezasartesanales

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.firebase.database.*
import com.google.gson.Gson


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

        viewManager = LinearLayoutManager(this)
        viewAdapter = miAdapter(listCervezas)

        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("Cervezas")

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listCervezas.clear()
                val gson= Gson()
                for (objj  in dataSnapshot.children){
                    val registro=objj.getValue()
                    try {
                        val reg:Cervezas=gson.fromJson(registro.toString(),Cervezas::class.java)
                        listCervezas.add(reg)
                    }
                    catch (e: com.google.gson.JsonSyntaxException) {}
                }
                recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        dbReference.child("cervezas").addValueEventListener(menuListener)
    }
}