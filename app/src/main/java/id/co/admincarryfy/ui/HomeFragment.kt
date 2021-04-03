package id.co.admincarryfy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Home
import id.co.admincarryfy.databinding.FragmentHomeBinding
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val dataBinding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHomeResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        setDataHome(it?.data)
                    }
                }
            }
        })

        viewModel.getHomeData()

    }

    private fun setDataHome(data: Home?) {
        dataBinding.tvCountOrder.text = data?.orderUser
        dataBinding.tvCountDriver.text = data?.sumDriver
        dataBinding.tvCountUser.text = data?.sumUser
    }
}