package com.dompetku

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class IncomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var sp: SharedPreferences
    private var _arKategori = ArrayList<dcKategori>()
    private val _arKategoriIncome = ArrayList<dcKategori>()
    private lateinit var _spKategoriIncome: Spinner
    private val _arIncome = ArrayList<dcIncome>()
    private var idIncome = 1

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IncomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        _spKategoriIncome = view.findViewById<Spinner>(R.id.spKategoriIncome)
        sp = requireContext().getSharedPreferences(
            "dataSP",
            Context.MODE_PRIVATE
        )
        ambilKategori()
        tampilkanSpinner()

        val _etNamaIncome = view.findViewById<EditText>(R.id.etNamaIncome)
        val _etNominalIncome = view.findViewById<EditText>(R.id.etNominalIncome)
        val _etTanggalIncome = view.findViewById<EditText>(R.id.etTanggalIncome)
        val _btnSimpanIncome = view.findViewById<Button>(R.id.btnSimpanIncome)

        _btnSimpanIncome.setOnClickListener {
            val keterangan =_etNamaIncome.text.toString().trim()
            val nominal =_etNominalIncome.text.toString().toLongOrNull()
            val tanggal =_etTanggalIncome.text.toString().trim()
            val posisiKategori =_spKategoriIncome.selectedItemPosition

            if (keterangan.isNotEmpty() && nominal != null && nominal > 0 && tanggal.isNotEmpty() && posisiKategori in _arKategoriIncome.indices) {
                val kategoriId = _arKategoriIncome[posisiKategori].id
                val income = dcIncome(
                    idIncome,
                    kategoriId,
                    keterangan,
                    nominal,
                    tanggal
                )
                _arIncome.add(income)
                idIncome++

                Toast.makeText(
                    requireContext(),
                    "Income berhasil ditambahkan",
                    Toast.LENGTH_SHORT
                ).show()

                _etNamaIncome.text.clear()
                _etNominalIncome.text.clear()
                _etTanggalIncome.text.clear()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Lengkapi data pemasukan dengan benar",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun ambilKategori() {
        val gson = Gson()
        val isiSP = sp.getString("spKategori", null)
        val type = object : TypeToken<ArrayList<dcKategori>>() {}.type

        if (isiSP != null) {
            _arKategori = gson.fromJson(isiSP, type)
        }

        _arKategoriIncome.clear()
        for (kategori in _arKategori) {
            if (kategori.jenis == "Income") {
                _arKategoriIncome.add(kategori)
            }
        }
    }

    private fun tampilkanSpinner() {
        val namaKategori = ArrayList<String>()

        for (kategori in _arKategoriIncome) {
            namaKategori.add(kategori.nama)
        }

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            namaKategori
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        _spKategoriIncome.adapter = adapter
    }
}