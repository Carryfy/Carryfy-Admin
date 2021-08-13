package id.co.admincarryfy.ui.driver.saldo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.databinding.ActivityUpdateSaldoBinding
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel


@AndroidEntryPoint
class UpdateSaldoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateSaldoBinding
    private val driverViewModel: DriverViewModel by viewModels()
    private var idDriver: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_saldo)

        val driverOld: Driver = intent.getParcelableExtra("driver")!!
        setDataDriver(driverOld)
        idDriver = driverOld.idDriverTravel!!

        binding.tvSimpan.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage("Apakah anda yakin untuk edit ?")
            alertDialog.setPositiveButton("Ya"
            ) { dialog, which ->
                editProses()
            }
            alertDialog.show()
        }

        driverViewModel.updateSaldoMutable.observe(this, Observer { response ->
            when(response){
                is Resource.Loading ->{

                }
                is Resource.Error ->{
                    Toast.makeText(this, "${response.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success ->{
                    response?.data?.let {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        })

    }

    private fun setDataDriver(driverOld: Driver) {
        binding.etSaldo.setText(driverOld.deposit.toString())
    }

    private fun editProses() {
        driverViewModel.updateSaldo(idDriver, binding.etSaldo.text.toString())
    }
}