package id.co.admincarryfy.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.viewbinding.library.activity.viewBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.databinding.ActivityLoginBinding
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.LoginViewModel

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val dataBinding: ActivityLoginBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        viewModel.getLoginAdminResponse.observe(this, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        Toast.makeText(this, "${it?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        dataBinding.btnSignIn.setOnClickListener {
            prosesLogin()
        }

    }

    private fun prosesLogin() {
        viewModel.getLoginAdmin(dataBinding.etEmail.text.toString(), dataBinding.etPassword.text.toString())
    }
}