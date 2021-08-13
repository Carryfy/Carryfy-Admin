package id.co.admincarryfy.ui.driver.profil

import android.content.DialogInterface
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
import id.co.admincarryfy.databinding.ActivityProfileDriverBinding
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class ProfileDriverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileDriverBinding
    private val driverViewModel: DriverViewModel by viewModels()
    private var idDriver: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_driver)

        val driverOld: Driver = intent.getParcelableExtra("driver")!!
        setDataDriver(driverOld)
        idDriver = driverOld.idDriverTravel!!

        driverViewModel.editDataDriverMutable.observe(this, Observer { response ->
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

        binding.tvEdit.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage("Apakah anda yakin untuk edit ?")
            alertDialog.setPositiveButton("Ya"
            ) { dialog, which ->
                editProses()
            }
            alertDialog.show()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

    }

    private fun editProses() {
        val driver = Driver()
        driver.idDriverTravel = idDriver
        driver.namaDriver = binding.etNamaDriver.text.toString()
        driver.noHpUtama = binding.etNoHpUtama.text.toString()
        driver.noHpCadangan = binding.etNoHpCadangan.text.toString()
        driver.jenisKelamin = binding.etJenisKelamin.text.toString()
        driver.noKtpSim = binding.etKtpSim.text.toString()
        driver.merkKendaraan = binding.etMerkKendaraan.text.toString()
        driver.warnaKendaraan = binding.etWarnaKendaraan.text.toString()
        driver.platKendaraan = binding.etPlatKendaraan.text.toString()
        driver.deposit = binding.etDeposit.text.toString()

        driverViewModel.editDataDriver(driver)
    }

    private fun setDataDriver(driver: Driver) {
        with(binding){
            etNamaDriver.setText(driver.namaDriver)
            etNoHpUtama.setText(driver.noHpUtama)
            etNoHpCadangan.setText(driver.noHpCadangan)
            etJenisKelamin.setText(driver.jenisKelamin)
            etKtpSim.setText(driver.noKtpSim)
            etMerkKendaraan.setText(driver.merkKendaraan)
            etWarnaKendaraan.setText(driver.warnaKendaraan)
            etPlatKendaraan.setText(driver.platKendaraan)
            etDeposit.setText(driver.deposit)
        }
    }
}