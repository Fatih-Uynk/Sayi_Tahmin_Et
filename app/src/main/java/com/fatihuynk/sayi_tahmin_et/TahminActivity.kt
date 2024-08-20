package com.fatihuynk.sayi_tahmin_et

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fatihuynk.sayi_tahmin_et.databinding.ActivityTahminBinding
import kotlin.random.Random

class TahminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTahminBinding

    private var rastgeleSayi = 0 //rastgeleSayi bu sayfaya geçiş yapıldığı anda oluşturulacak

    private var sayac = 5 //5 tane hakkımız var, bu sayaç onu kontrol edecek

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTahminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rastgeleSayi = Random.nextInt(101)//0 ile 100 arasında sayı üretir ve rastgeleSayi içine bunu aktarır

        Log.e("Rastgele Sayı",rastgeleSayi.toString())//Konsola yazdırdık, Logcat de gözükür rastgele ürettiğimiz sayı
        //rastgeleSayi, sayısal bir değer olduğu için String ifadeye dönüştürmemiz gerekiyor bunun için toString kullandık


        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonTahmin.setOnClickListener{

            sayac = sayac - 1 //Her tahmin butonuna basıldğında sayacı bir azaltmış olacağız ve kalan haklar kontrol edilecek

            //editText'den tahmini aldık aşağıda ki satırda
            val tahmin = binding.editTextGirdi.text.toString().toInt()//editTextGirdi.text, editText'den veriyi editable olarak aldık
            // Sonra string ifadeye dönüştürdük ve sonrada Int'e dönüştürdük çünkü rastgeleSayi integer.
            //Kıyaslarken Int olarak kıyaslamam gerekiyor

            if (tahmin == rastgeleSayi){

                //Sayfalar arasında geçiş yaptığımız kod hemen altında
               val intent = Intent(this@TahminActivity,SonucActivity::class.java)
                //şu an nerede olduğum sayfa:this@TahminActivity
                // nereye, yani hangi sayfaya geçeceğim sayfa :SonucActivity

                //Sayfa geçişi olurken veri geçişi(transferi) yapmamız gerekiyor,hemen altında ki satır da kodlar:
                intent.putExtra("sonuc",true)
                //diğer sayfaya geçiş yaptğımda, karşı taraftan bu veriyi aldıklarında true cevabını alsınlar
                //Sonuç olarak tahmin doğru bilindiği için kazandınız yazısının olduğu sayfa açılır

                startActivity(intent)
                //startActivity() yeni bir Intent oluşturur ve ardından o Intent üzerinden işlem yapar.


                finish()//Tahmin ekranından sonuç ekranına geçerken bu sayfayı BackStak'den sileceğiz
                //Silmezsek geri tuşuna bastığımızda tahmin sayfasına geri döneriz.
                //Geri tuşuna basınca ana ekrana dönüş yapmasını istiyorum bu yüzden finis() metodunu kullandım

                return@setOnClickListener //return@setOnClickListener, kullanmazsak aşağıda ki kodlar çalışmaya devam eder.
            // ve sonunda sayacın sıfıra eşit olduğu blok çalışır ve sonucu doğru girsek dahi bizi kaybettiniz sayfasına yönlendirir.
                //Butona basıldğı anda diğer bloklar çalışmaz, sadece burada'ki ler çalışır ve sonunda butonun çalışması durur
            }

            if (tahmin > rastgeleSayi){

                binding.textViewYardim.text = "Azalt"
                binding.textViewKalanHak.text = "Kalan Hak : $sayac"
            }

            if (tahmin < rastgeleSayi){

                binding.textViewYardim.text = "Arttır"
                binding.textViewKalanHak.text = "Kalan Hak : $sayac"
            }

            if (sayac == 0){//Bütün hakları bitirdiğimizde çalışır ve kaybettiğimizi gösteren sayfa gelir

                //Sayfalar arasında geçiş yaptığımız kod hemen altında
               val intent = Intent(this@TahminActivity,SonucActivity::class.java)
                //şu an nerede olduğum sayfa:this@TahminActivity
                // nereye yani hangi sayfaya geçeceğim sayfa :SonucActivity

                //Sayfa geçişi olurken veri geçişi(transferi) yapmamız gerekiyor,hemen altında ki satır da kodlar:
                intent.putExtra("sonuc",false)
                //diğer sayfaya geçiş yaptğımda, karşı taraftan bu veriyi aldıklarında false cevabını alsınlar
                //Sonuç olarak tahmin yanlış bilindiği için kaybettiniz yazısının olduğu sayfa açılır

                startActivity(intent)


                finish()//Tahmin ekranından sonuç ekranına geçerken bu sayfayı BackStak'den sileceğiz
                //Silmezsek geri tuşuna bastığımızda tahmin sayfasına geri döneriz.
                //Geri tuşuna basınca ana ekrana dönüş yapmasını istiyorum bu yüzden finis() metodunu kullandım
            }

            binding.editTextGirdi.setText("")//Yukarıda editText'den girdiğimiz veri içinde kalır
            //İçini boş bıraktık yenibir veri girildiği zaman içerisi sıfırlansın ve yeni den veri girebiliriz bu sayede
            //Böylece en son yazdığım veri silinir ve yeni veri girebiliriz
        }
    }
}