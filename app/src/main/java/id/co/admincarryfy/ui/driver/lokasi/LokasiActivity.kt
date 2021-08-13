package id.co.admincarryfy.ui.driver.lokasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.data.model.Lokasi
import id.co.admincarryfy.databinding.ActivityLokasiBinding
import id.co.admincarryfy.ui.adapter.LokasiAdapter
import id.co.admincarryfy.ui.adapter.RuteAdapter
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel
import java.util.ArrayList

@AndroidEntryPoint
class LokasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLokasiBinding
    private val viewModel: DriverViewModel by viewModels()
    private val adapter: LokasiAdapter by lazy {
        LokasiAdapter{lat, lng ->
            showMaps(lat, lng)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lokasi)

        val driver: Driver = intent.getParcelableExtra("driver")!!

        setupAdapter()
        viewModel.getLokasiMutable.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        setDataLokasi(it?.data)
                    }
                }
            }
        })

        viewModel.getLokasi(driver.noHpUtama!!)

    }

    private fun setDataLokasi(data: List<Lokasi>?) {
        if (data != null) {
            adapter.setLokasiList(data)
        }
    }

    private fun showMaps(lat: String, lng: String){
        val intent = Intent(this, MapsDriverActivity::class.java)
        intent.putExtra("lat", lat)
        intent.putExtra("lng", lng)
        startActivity(intent)
    }

    private fun setupAdapter() {
        with(binding){
            rvLokasi.layoutManager = LinearLayoutManager(this@LokasiActivity)
            rvLokasi.adapter = adapter
        }
    }

}