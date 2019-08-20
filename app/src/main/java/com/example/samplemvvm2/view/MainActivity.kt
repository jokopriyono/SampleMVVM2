package com.example.samplemvvm2.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samplemvvm2.R
import com.example.samplemvvm2.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val countriesAdapter = CountryListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.countries.observe(this, Observer {
            recycler.visibility = VISIBLE
            countriesAdapter.updateCountries(it)
        })

        viewModel.countyLoadError.observe(this, Observer {
            txt_error.visibility = if (it) VISIBLE else GONE
        })

        viewModel.loading.observe(this, Observer {
            loading.visibility = if (it) VISIBLE else GONE
            if (it) {
                txt_error.visibility = GONE
                recycler.visibility = GONE
            }
        })
    }
}
