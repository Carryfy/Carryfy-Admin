package id.co.admincarryfy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Home
import id.co.admincarryfy.data.model.Pesanan
import id.co.admincarryfy.databinding.FragmentHomeBinding
import id.co.admincarryfy.ui.adapter.PesananHomeAdapter
import id.co.admincarryfy.util.Constant
import id.co.admincarryfy.util.Resource
import id.co.admincarryfy.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var dataBinding: FragmentHomeBinding;
    private lateinit var orderAdapter: PesananHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getHomeResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        setDataHome(it?.data)
                    }
                }
            }
        })

        viewModel.getOrderTodayResponse.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    response.data.let {
                        setDataOrder(it?.data)
                    }
                }
                is Resource.Error ->{
                    Toast.makeText(context, "${response.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        getDataHome()
    }

    private fun getDataHome() {
        val date = Constant.getdateToday().split("_")
        viewModel.getHomeData()
        viewModel.getOrderTodayData(date[0], date[1])
    }

    private fun initRecyclerView() {
        orderAdapter = PesananHomeAdapter(requireContext())
        dataBinding.rvOrder.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = orderAdapter
        }
    }

    private fun setDataOrder(data: List<Pesanan>?) {
        data?.let { orderAdapter.setData(it) }
    }

    private fun setDataHome(data: Home?) {
        dataBinding.tvCountOrder.text = data?.orderUser
        dataBinding.tvCountDriver.text = data?.sumDriver
        dataBinding.tvCountUser.text = data?.sumUser
    }
}