package com.dicoding.submissionaplikasisederhana

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PERSON = "extra_person"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("List Film")

        val img: ImageView = findViewById(R.id.img_item_photo)
        val title: TextView = findViewById(R.id.tv_item_name)
        val description: TextView = findViewById(R.id.tv_item_description)
        val penjualan: TextView = findViewById(R.id.tv_item_penjualan)
        val rating: TextView = findViewById(R.id.tv_item_rating)
        val btnShare: Button = findViewById(R.id.tv_btn_share)

        findViewById<Button>(R.id.tv_btn_share)
        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            startActivity(Intent.createChooser(shareIntent, "Bagikan dengan"))
        }

        val film = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_PERSON, Film::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_PERSON)
        }

        if (film != null) {
            img.setImageResource(film.photo)
            title.text = film.name
            description.text = film.description
            penjualan.text = film.penjualan
            rating.text = film.rating
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.tv_btn_share -> {
                val intentDetail = Intent(this@DetailActivity, BiodataActivity::class.java)
                startActivity(intentDetail)
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}