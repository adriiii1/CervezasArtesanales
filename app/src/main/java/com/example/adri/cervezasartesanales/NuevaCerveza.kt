package com.example.adri.cervezasartesanales

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.cerveza_nueva.*
import com.google.firebase.database.FirebaseDatabase

class NuevaCerveza : AppCompatActivity() {

    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cerveza_nueva)

        database = FirebaseDatabase.getInstance()
        dbReference = database.getReference("Cervezas")

        btIns.setOnClickListener {
            loadDatabase(dbReference)
        }
    }

    fun loadDatabase(firebaseData: DatabaseReference) {
            val cerveza=Cervezas(txtId.text.toString().toLong(),txtNom.text.toString(),
                txtAp.text.toString(),txtVol.text.toString(),txtPrecio.text.toString().toDouble())
            val key = firebaseData.child("cervezas").push().key
            firebaseData.child("cervezas").child(key!!).setValue(cerveza)
    }


}
