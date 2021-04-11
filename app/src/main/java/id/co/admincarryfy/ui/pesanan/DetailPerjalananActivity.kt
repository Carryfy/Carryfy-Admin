package id.co.admincarryfy.ui.pesanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.data.model.Pesanan
import id.co.admincarryfy.databinding.ActivityDetailPerjalananBinding
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class DetailPerjalananActivity : AppCompatActivity() {

    private lateinit var dataBinding : ActivityDetailPerjalananBinding
    private val viewModel: DriverViewModel by viewModels()

    companion object{
        var driver : Driver?= null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_perjalanan)

        val pesanan = intent.getSerializableExtra("pesanan") as Pesanan

        dataBinding.data = pesanan


        dataBinding.btnUpdateDriver.setOnClickListener {
            val intent = Intent(this, PilihDriverActivity::class.java)
            intent.putExtra("lok_tujuan", pesanan.lokTujuan)
            intent.putExtra("lok_penjemputan", pesanan.lokPenjemputan)
            startActivity(intent)
        }


        viewModel.updatePesananUserMutable.observe(this, Observer { response ->
            when(response){
                is Resource.Success ->{
                    Toast.makeText(this, "${response?.data?.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error ->{
                    Toast.makeText(this, "${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })

        dataBinding.btnUpdate.setOnClickListener {
            viewModel.updatePesananUserData(pesanan.idPesanan!!, driver?.noHpUtama!!)
        }


    }


    override fun onResume() {
        super.onResume()
        if(driver != null){
            dataBinding.tvUpdateDriver.text = driver?.namaDriver
        }
    }
}