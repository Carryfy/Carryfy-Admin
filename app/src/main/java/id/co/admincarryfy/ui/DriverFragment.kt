package id.co.admincarryfy.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.databinding.FragmentDriverBinding
import id.co.admincarryfy.ui.adapter.DriverAdapter
import id.co.admincarryfy.ui.driver.AddDriverActivity
import id.co.admincarryfy.ui.driver.AddPerjalananDriverActivity
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.HomeViewModel

@AndroidEntryPoint
class DriverFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var dataBinding: FragmentDriverBinding
    private lateinit var driverAdapter: DriverAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_driver, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDriverResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        setDataDriver(it?.data)
                    }
                }
            }
        })

        dataBinding.fbDriver.setOnClickListener {
            findNavController().navigate(R.id.action_driverFragment_to_addDriverActivity)
        }

        initRecyclerView()
        getDataDriver()
    }

    private fun setDataDriver(data: List<Driver>?) {
        data?.let {
            driverAdapter.setData(it)
        }
    }

    private fun getDataDriver() {
        viewModel.getDriverData()
    }

    private fun initRecyclerView() {
        driverAdapter = DriverAdapter(requireContext())
        dataBinding.rvDriver?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = driverAdapter
        }
    }
}