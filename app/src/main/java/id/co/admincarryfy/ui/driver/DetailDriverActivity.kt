package id.co.admincarryfy.ui.driver

import android.content.Intent
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
import id.co.admincarryfy.ui.driver.lokasi.LokasiActivity
import id.co.admincarryfy.ui.driver.profil.ProfileDriverActivity
import id.co.admincarryfy.ui.driver.riwayat.RiwayatActivity
import id.co.admincarryfy.ui.driver.rute.RuteDriverActivity
import id.co.admincarryfy.ui.driver.saldo.UpdateSaldoActivity
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.DriverViewModel

@AndroidEntryPoint
class DetailDriverActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityDetailDriverBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail_driver)

        val driver: Driver = intent.getParcelableExtra("driver")!!
        dataBinding.tvDriver.text = driver.namaDriver
        dataBinding.tvSaldo.text = driver.deposit

        dataBinding.ivProfil.setOnClickListener {
            val intent = Intent(this, ProfileDriverActivity::class.java)
            intent.putExtra("driver", driver)
            startActivity(intent)
        }

        dataBinding.ivRute.setOnClickListener {
            val intent = Intent(this, RuteDriverActivity::class.java)
            intent.putExtra("driver", driver)
            startActivity(intent)
        }

        dataBinding.ivRiwayat.setOnClickListener {
            val intent = Intent(this, RiwayatActivity::class.java)
            intent.putExtra("driver", driver)
            startActivity(intent)
        }

        dataBinding.btnSaldo.setOnClickListener {
            val intent = Intent(this, UpdateSaldoActivity::class.java)
            intent.putExtra("driver", driver)
            startActivity(intent)
        }

        dataBinding.ivLokasi.setOnClickListener {
            val intent = Intent(this, LokasiActivity::class.java)
            intent.putExtra("driver", driver)
            startActivity(intent)
        }

    }

}