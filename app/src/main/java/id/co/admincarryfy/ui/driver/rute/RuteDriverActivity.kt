package id.co.admincarryfy.ui.driver.rute

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
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ActivityRuteDriverBinding
import id.co.admincarryfy.ui.adapter.RuteAdapter
import id.co.admincarryfy.ui.driver.DetailDriverActivity
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class RuteDriverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRuteDriverBinding
    private val viewModel: DriverViewModel by viewModels()

    private val adapter: RuteAdapter by lazy {
        RuteAdapter{
            showDetail(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rute_driver)

        val driver: Driver = intent.getParcelableExtra("driver")!!

        setupAdapter()

        viewModel.getPerjalananRequest.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data.let {
                        setDataPerjalanan(it?.data)
                    }
                }
            }
        })

        viewModel.getPerjalananData(driver.noHpUtama!!)
    }

    private fun setupAdapter() {
        with(binding){
            rvRute.layoutManager = LinearLayoutManager(this@RuteDriverActivity)
            rvRute.adapter = adapter
        }
    }

    private fun setDataPerjalanan(data: List<Perjalanan>?) {
        if (data != null) {
            adapter.setListRute(data)
        }
    }

    private fun showDetail(perjalanan: Perjalanan){
        val intent = Intent(this, DetailRuteActivity::class.java)
        intent.putExtra("perjalanan", perjalanan)
        startActivity(intent)
    }

}
