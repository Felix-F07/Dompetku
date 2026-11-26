package com.dompetku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class KategoriFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private val _arKategori = ArrayList<dcKategori>()
    private var idKategori = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_kategori, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KategoriFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _rgJenis = view.findViewById<RadioGroup>(R.id.rgJenis)
        val _etNamaKategori = view.findViewById<EditText>(R.id.etNamaKategori)
        val _btnSimpanKategori = view.findViewById<Button>(R.id.btnSimpanKategori)

        _btnSimpanKategori.setOnClickListener {
            val nama = _etNamaKategori.text.toString().trim()
            val jenisDipilih = _rgJenis.checkedRadioButtonId

            if (nama.isNotEmpty() && jenisDipilih != -1) {
                val radioButton = view.findViewById<RadioButton>(jenisDipilih)
                val jenis = radioButton.text.toString()

                val kategori = dcKategori(
                    idKategori,
                    nama,
                    jenis
                )

                _arKategori.add(kategori)

                idKategori++

                Toast.makeText(
                    requireContext(),
                    "Kategori berhasil ditambahkan",
                    Toast.LENGTH_SHORT
                ).show()

                _etNamaKategori.text.clear()
                _rgJenis.clearCheck()
            }else{
                Toast.makeText(
                    requireContext(),
                    "Lengkapi data kategori",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}