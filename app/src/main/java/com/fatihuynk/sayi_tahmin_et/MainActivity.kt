package com.fatihuynk.sayi_tahmin_et

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fatihuynk.sayi_tahmin_et.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonBasla.setOnClickListener{
            //Sayfalar arasında geçiş yaptığımız kod hemen altında
            startActivity(Intent(this@MainActivity,TahminActivity::class.java))//şu an nerede olduğum sayfa:this@MainActivity
        // nereye yani hangi sayfaya geçeceğim sayfa :TahminActivity

        }
    }
}