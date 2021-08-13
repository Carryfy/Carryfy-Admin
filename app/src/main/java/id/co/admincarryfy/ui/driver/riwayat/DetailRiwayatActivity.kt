package id.co.admincarryfy.ui.driver.riwayat

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.data.model.Riwayat
import id.co.admincarryfy.databinding.ActivityDetailRiwayatBinding
import kotlinx.android.synthetic.main.activity_detail_riwayat.*
import kotlinx.android.synthetic.main.activity_detail_rute.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetailRiwayatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRiwayatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_riwayat)


        val riwayat: Riwayat = intent.getParcelableExtra("riwayat")!!
        setDataRiwayat(riwayat)

    }

    private fun setDataRiwayat(riwayat: Riwayat) {
        with(binding){
            tvPenumpang.text = riwayat.user.nama_user
            tv_no_hp_user.text = riwayat.no_hp_user
            tvJemput.text = riwayat.lok_penjemputan
            tvTujuan.text = riwayat.lok_tujuan
            try{
                val SDFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                val cal = Calendar.getInstance()
                cal.time = SDFormat.parse(riwayat.tgl_berangkat)

                tvBerangkat.text = DateFormat.format("dd/MM/yyyy HH:mm", cal).toString()

                val sampai = DateFormat.format("dd/MM/yyyy HH:mm", riwayat.tgl_sampai).toString()
                tvSampai.text = sampai

                tvDewasa.text = riwayat.penumpang_dewasa
                tvBarang.text = riwayat.barang_bawaan
                tvBiaya.text = riwayat.biaya

            }catch (e: Exception){
                e.printStackTrace()
            }

            val geocoder = Geocoder(this@DetailRiwayatActivity)
            val addresses: List<Address>
            try {
//                addresses = geocoder.getFromLocationName(locationHospital, 5)
//                if (addresses.size > 0) {
//                    val locationPangkalan = addresses[0]
//
//
//                }
            } catch (e: IOException) {
                Log.d("ERROR ROUTE", "displayLocation: $e")
            }

        }
    }
}