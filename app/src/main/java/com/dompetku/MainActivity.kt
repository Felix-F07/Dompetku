package com.dompetku

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val _btnKategori=findViewById<Button>(R.id.btnKategori)
        val _btnIncome=findViewById<Button>(R.id.btnIncome)
        val _btnExpense=findViewById<Button>(R.id.btnExpense)

        if (savedInstanceState == null) {
            replaceFragment(KategoriFragment())
        }

        _btnKategori.setOnClickListener {
            replaceFragment(KategoriFragment())
        }

        _btnIncome.setOnClickListener {
            replaceFragment(IncomeFragment())
        }

        _btnExpense.setOnClickListener {
            replaceFragment(ExpenseFragment())
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment)

        fragmentTransaction.commit()
    }
}