package id.co.admincarryfy.ui.driver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityRetainedScoped
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ActivityDetailDriverBinding
import id.co.admincarryfy.ui.adapter.AddPerjalananAdapter
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class DetailDriverActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityDetailDriverBinding
    private val viewModel: DriverViewModel by viewModels()
    private lateinit var perjalananAdapter: AddPerjalananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_driver)

        val driver: Driver = intent.getParcelableExtra("driver")!!
        dataBinding.data = driver

        initRecyclerView()

        viewModel.getPerjalananRequest.observe(this, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        setDataPerjalanan(it?.data)
                    }
                }
            }
        })

        viewModel.getPerjalananData(driver.noHpUtama!!)

    }

    private fun initRecyclerView() {
        perjalananAdapter = AddPerjalananAdapter(this)
        dataBinding.rvPerjalanan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = perjalananAdapter
        }
    }

    private fun setDataPerjalanan(data: List<Perjalanan>?) {
        perjalananAdapter.setData(data!!)
    }
}