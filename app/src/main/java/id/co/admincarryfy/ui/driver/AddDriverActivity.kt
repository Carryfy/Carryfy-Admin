package id.co.admincarryfy.ui.driver

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
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ActivityAddDriverBinding
import id.co.admincarryfy.ui.adapter.AddPerjalananAdapter
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class AddDriverActivity : AppCompatActivity() {

    private val driverViewModel: DriverViewModel by viewModels()
    private var _binding: ActivityAddDriverBinding ?= null
    private val binding get()= _binding!!

    private lateinit var perjalananAdapter: AddPerjalananAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_add_driver)

        initRecylerView()

        loadPerjalanan()

        driverViewModel.addDriverRequest.observe(this, Observer { response ->
            when(response){
                is Resource.Loading ->{

                }
                is Resource.Error ->{
                    Toast.makeText(this, "${response.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success ->{
                    response?.data?.let {
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        binding.tvTambahPerjalanan.setOnClickListener {
            val intent = Intent(this, AddPerjalananDriverActivity::class.java)
            startActivity(intent)
        }

        binding.tvSimpan.setOnClickListener {
            inputProses()
        }

    }

    private fun inputProses() {
        val driver = Driver()
        driver.namaDriver = binding.etNamaDriver.text.toString()
        driver.noHpUtama = binding.etNoHpUtama.text.toString()
        driver.noHpCadangan = binding.etNoHpCadangan.text.toString()
        driver.jenisKelamin = binding.etJenisKelamin.text.toString()
        driver.noKtpSim = binding.etKtpSim.text.toString()
        driver.merkKendaraan = binding.etMerkKendaraan.text.toString()
        driver.warnaKendaraan = binding.etWarnaKendaraan.text.toString()
        driver.platKendaraan = binding.etPlatKendaraan.text.toString()
        driver.deposit = binding.etDeposit.text.toString()

        driverViewModel.addDriverData(driver)
    }

    private fun loadPerjalanan() {
        driverViewModel.getPerjalananDatabase.observe(this, Observer {
            val perjalananList = mutableListOf<Perjalanan>()
            for(perjalanan in it){
                perjalananList.add(perjalanan.perjalanan)
            }
            perjalananAdapter.setData(perjalananList)
        })
    }

    private fun initRecylerView() {
        perjalananAdapter = AddPerjalananAdapter(this)
        binding.rvPerjalanan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = perjalananAdapter
        }
    }
}