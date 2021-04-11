package id.co.admincarryfy.ui.pesanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.databinding.ActivityPilihDriverBinding
import id.co.admincarryfy.ui.OnClickDriverByLokasiListener
import id.co.admincarryfy.ui.adapter.DriverAdapter
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class PilihDriverActivity : AppCompatActivity(), OnClickDriverByLokasiListener {

    private val viewModel: DriverViewModel by viewModels()
    private lateinit var dataBinding: ActivityPilihDriverBinding
    private lateinit var driverAdapter: DriverAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_pilih_driver)

        driverAdapter = DriverAdapter(this, DriverAdapter.driverByLokasi, this)

        initRecyclerView()

        viewModel.getDriverByLokasiRequest.observe(this, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        setDataDriver(it?.data)
                    }
                }
            }
        })

        getDataDriver()


    }

    private fun setDataDriver(data: List<Driver>?) {
        data?.let {
            driverAdapter.setData(it)
        }
    }

    private fun initRecyclerView() {
        dataBinding.rvDriver.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = driverAdapter
        }
    }

    private fun getDataDriver() {
        val lokPenjemputan = intent.getStringExtra("lok_penjemputan")
        val lokTujuan = intent.getStringExtra("lok_tujuan")
        viewModel.getDriverByLokasiData(lokPenjemputan!!, lokTujuan!!)
    }

    override fun onClicDriverListener(driver: Driver) {
        DetailPerjalananActivity.driver = driver
        finish()
    }
}