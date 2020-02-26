package org.d3ifcool1050.praassesment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3ifcool1050.praassesment.databinding.FragmentSegitigaSikuBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaSikuFragment : Fragment() {
    var hasilLuasS = 0
    var hasilKelS = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentSegitigaSikuBinding>(inflater, R.layout.fragment_segitiga_siku, container, false)

        if (savedInstanceState != null){
            hasilLuasS = savedInstanceState.getInt("Luas")
            hasilKelS = savedInstanceState.getInt("Keliling")
            binding.tvHasilLuas1.text =hasilLuasS.toString()
            binding.tvHasilKeliling1.text = hasilKelS.toString()
            binding.tvHasilLuas1.text =hasilLuasS.toString()
            binding.tvHasilKeliling1.text = hasilKelS.toString()
        }

        binding.bHitung1.setOnClickListener {
           var hasilLuasSegitiga = hitungLuasSegitigaSiku(binding.etAlas.text.toString().toInt(), binding.etTinggi.text.toString().toInt())
            var hasilKelilingSegitiga =hitungKelilingSegitigaSiku(binding.etAlas.text.toString().toInt(), binding.etTinggi.text.toString().toInt())
            hasilLuasS = hasilLuasSegitiga.toInt()
            hasilKelS = hasilKelilingSegitiga.toInt()
            binding.tvHasilLuas1.text =hasilLuasS.toString()
            binding.tvHasilKeliling1.text = hasilKelS.toString()
        }
        binding.bShare1.setOnClickListener {
            val pesan =
                "Hasil perhitungan segitiga: \nLuas = ${binding.tvHasilLuas1.text} \nKeliling = ${binding.tvHasilKeliling1.text}"
            startActivity(intentShare(pesan))
        }
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("Luas", hasilLuasS)
        outState.putInt("Keliling", hasilKelS)
        super.onSaveInstanceState(outState)
    }

    fun hitungLuasSegitigaSiku(alas: Int, tinggi: Int):Double{
        return (alas * tinggi / 2).toDouble()
    }
    fun hitungKelilingSegitigaSiku(alas: Int, tinggi: Int):Double{
        var miring = Math.sqrt((alas * alas) + (tinggi * tinggi).toDouble())
        return (alas + tinggi + miring)
    }
    fun intentShare(pesan: String): Intent{
    val intentShare = Intent(Intent.ACTION_SEND)
        intentShare.setType("text/plain").putExtra(Intent.EXTRA_TEXT, pesan)
        return intentShare
    }
}
