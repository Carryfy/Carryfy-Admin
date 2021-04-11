package id.co.admincarryfy.ui.driver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.database.entities.PerjalananEntities
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ActivityAddDriverBinding
import id.co.admincarryfy.databinding.ActivityAddPerjalananDriverBinding
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class AddPerjalananDriverActivity : AppCompatActivity() {

    private val driverViewModel: DriverViewModel by viewModels()
    private var _binding: ActivityAddPerjalananDriverBinding?= null
    private val binding get()= _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_add_perjalanan_driver)

        binding.tvSimpan.setOnClickListener {
            insertPerjalanan()
        }

    }

    private fun insertPerjalanan() {
        val perjalanan = Perjalanan(
            binding.etLokasiPenjemputan.text.toString(),
            binding.etLokasiTujuan.text.toString(),
            binding.etJamBerangkat.text.toString(),
            binding.etJamSampai.text.toString(),
            binding.etHariKeberangkatan.text.toString(),
            binding.etBiaya.text.toString()
        )
        val perjalananEntities = PerjalananEntities(0, perjalanan)
        driverViewModel.insertPerjalananDatabase(perjalananEntities)
        finish()

    }
}