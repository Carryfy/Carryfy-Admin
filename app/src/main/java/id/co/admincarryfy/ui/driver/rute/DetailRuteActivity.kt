package id.co.admincarryfy.ui.driver.rute

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
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ActivityDetailRuteBinding
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class DetailRuteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRuteBinding
    private var idRute: String = ""
    private val driverViewModel: DriverViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_rute)


        val ruteOld: Perjalanan = intent.getParcelableExtra("perjalanan")!!
        idRute = ruteOld.idDetailRuteDriver!!
        setDataRute(ruteOld)

        binding.tvEdit.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setMessage("Apakah anda yakin untuk edit ?")
            alertDialog.setPositiveButton("Ya"
            ) { dialog, which ->
                editPerjalanan()
            }
            alertDialog.show()
        }

        driverViewModel.editRuteDriverMutable.observe(this, Observer { response ->
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

    private fun setDataRute(ruteOld: Perjalanan) {
        with(binding){
            etLokasiPenjemputan.setText(ruteOld.lokPenjemputan)
            etLokasiTujuan.setText(ruteOld.lokTujuan)
            etJamBerangkat.setText(ruteOld.jamBerangkat)
            etJamSampai.setText(ruteOld.jamSampai)
            etHariKeberangkatan.setText(ruteOld.hariKeberangkatan)
            etBiaya.setText(ruteOld.biaya)
        }
    }

    private fun editPerjalanan() {
        val perjalanan = Perjalanan(
            idRute,
            binding.etLokasiPenjemputan.text.toString(),
            binding.etLokasiTujuan.text.toString(),
            binding.etJamBerangkat.text.toString(),
            binding.etJamSampai.text.toString(),
            binding.etHariKeberangkatan.text.toString(),
            binding.etBiaya.text.toString()
        )
        driverViewModel.editRuteDriver(perjalanan)
    }
}