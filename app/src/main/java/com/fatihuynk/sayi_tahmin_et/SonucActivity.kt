package com.fatihuynk.sayi_tahmin_et

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fatihuynk.sayi_tahmin_et.databinding.ActivitySonucBinding

class SonucActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySonucBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySonucBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Bu sayfaya geçiş yapıldığı zaman bize gönderilen veriyi almamız gerekiyor, veri transferinin gerçekleşmesi için.
        val sonuc = intent.getBooleanExtra("sonuc",false)//varsayılan değerdir false burada.
        //getBooleanExtra kullanmamızın sebebi veri true - false olarak gönderildiği için.

        if (sonuc){//Sonuç true ise kazanmışızdır dolayısıyla true olduğunda bu blok çalışır

            binding.textViewSonuc.text = "KAZANDINIZ"
            binding.imageViewSonuc.setImageResource(R.drawable.gulen_resim)

        }else{//false olduğu durumda çalışacak yani kaybettiğimiz zaman çalışacak

            binding.textViewSonuc.text = "KAYBETTİNİZ"
            binding.imageViewSonuc.setImageResource(R.drawable.uzgun_resim)
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonTekrar.setOnClickListener{
            //Sayfalar arasında geçiş yaptığımız kod hemen altında
            startActivity(Intent(this@SonucActivity,TahminActivity::class.java))
            //şu an nerede olduğum sayfa:this@SonucActivity
            // nereye yani hangi sayfaya geçeceğim sayfa :TahminActivity

            finish()//Bu sayfa da finish() metodu kullanmamın sebebi : Tekrar Oyna butonuna bastığımda Tahmin ekranına gideceğiz
            //Tahmin ekranındayken sonuç ekranından geldiğim için sonuç ekranı backStack de kalmış olacak
            //Dolayısıyla finis() metodu kullanarak buna engel olmuş oluyoruz
            //Böylece Tahmin sayfasındayken geri tuşuna bastığımda ana sayfaya yönlendirileceğiz.
        }
    }
}