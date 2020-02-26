package org.d3ifcool1050.praassesment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.fragment_persegi_panjang.*
import org.d3ifcool1050.praassesment.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {
    var hasilLuas = 0
    var hasilKeliling = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentPersegiPanjangBinding>(inflater, R.layout.fragment_persegi_panjang, container, false)

        if (savedInstanceState != null){
            hasilLuas = savedInstanceState.getInt("Luas")
            hasilKeliling = savedInstanceState.getInt("Keliling")
            binding.tvHasilLuas.text =hasilLuas.toString()
            binding.tvHasilKeliling.text = hasilKeliling.toString()
        }

        binding.bHitung.setOnClickListener {
           var hasilLuasPersegi = hitungLuasPersegiPanjang(binding.etPanjang.text.toString().toInt(), binding.etLebar.text.toString().toInt())
            var hasilKelilingPersegi = hitungKelilingPersegiPanjang(binding.etPanjang.text.toString().toInt(), binding.etLebar.text.toString().toInt())
            hasilLuas = hasilLuasPersegi.toInt()
            hasilKeliling = hasilKelilingPersegi.toInt()
            binding.tvHasilLuas.text =hasilLuas.toString()
            binding.tvHasilKeliling.text = hasilKeliling.toString()
        }
        binding.bShare.setOnClickListener {
            val pesan =
                "Hasil perhitungan segitiga: \nLuas = ${binding.tvHasilLuas.text} \nKeliling = ${binding.tvHasilKeliling.text}"
            startActivity(intentShare(pesan))
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("Luas", hasilLuas)
        outState.putInt("Keliling", hasilKeliling)
        super.onSaveInstanceState(outState)
    }

    fun  hitungLuasPersegiPanjang(panjang: Int, lebar: Int):Double{
        return (panjang * lebar).toDouble()
    }
    fun  hitungKelilingPersegiPanjang(panjang: Int, lebar: Int):Double{
        return ((2 * panjang) + (2 * lebar)).toDouble()
    }
    fun intentShare(pesan: String): Intent {
        val intentShare = Intent(Intent.ACTION_SEND)
        intentShare.setType("text/plain").putExtra(Intent.EXTRA_TEXT, pesan)
        return intentShare
    }

}
