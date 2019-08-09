package com.example.condominiofb

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        database = FirebaseDatabase.getInstance()
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        btCondominio.setOnClickListener { view ->
            addCondominio(txtCondominio.text.toString())
        }

        btNui.setOnClickListener { view ->
            addUI(txtUI.text.toString(), txtCondominio.text.toString())
        }

        btQrCode.setOnClickListener{ view ->
            startActivity(Intent ( baseContext, SimpleScannerActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addCondominio( cond: String) {

            val path = database.getReference("condominio/" + cond)

            val condominio = Condominio ( cond, "onde", "13.383.247/0001-86")

            path.setValue(condominio)
    }

    fun addUI(ui : String, cond: String){

        val path = database.getReference("condominio/" + cond + "/uis/" + ui)

       // val condominio = Condominio ( cond, "onde", "13.383.247/0001-86")

        path.setValue("Aqui é a unidade")
    }
}
