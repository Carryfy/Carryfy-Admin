package id.co.admincarryfy.ui.driver.riwayat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.data.model.Riwayat
import id.co.admincarryfy.databinding.ActivityRiwayatBinding
import id.co.admincarryfy.ui.adapter.RiwayatAdapter
import id.co.admincarryfy.ui.driver.rute.DetailRuteActivity
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class RiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRiwayatBinding
    private val viewModel: DriverViewModel by viewModels()
    private val adapter : RiwayatAdapter by lazy {
        RiwayatAdapter{
            showDetail(it)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_riwayat)

        val driver: Driver = intent.getParcelableExtra("driver")!!

        setupAdapter()

        viewModel.riwayatDriverMutable.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        setData(it?.data)
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.riwayatDriver(driver.noHpUtama!!)

    }

    private fun setupAdapter() {
        with(binding){
            rvRiwayat.layoutManager = LinearLayoutManager(this@RiwayatActivity)
            rvRiwayat.adapter = adapter
        }
    }

    private fun setData(data: List<Riwayat>?) {
        if (data != null) {
            adapter.setListRiwayat(data)
        }
    }

    private fun showDetail(riwayat: Riwayat){
        val intent = Intent(this, DetailRiwayatActivity::class.java)
        intent.putExtra("riwayat", riwayat)
        startActivity(intent)
    }

}