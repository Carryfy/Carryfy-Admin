package id.co.admincarryfy.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.databinding.ItemDriverBinding
import id.co.admincarryfy.ui.OnClickDriverByLokasiListener
import id.co.admincarryfy.ui.driver.DetailDriverActivity
import id.co.admincarryfy.util.DiffUtilCustom

class DriverAdapter(val context: Context,val  driverType: Int, val onClickDriverByLokasiListener: OnClickDriverByLokasiListener): RecyclerView.Adapter<DriverAdapter.ViewHolder>() {

    private var dataDriverList = emptyList<Driver>()

    companion object{
        val driverAll = 1
        val driverByLokasi = 2
    }

    inner class ViewHolder(val binding: ItemDriverBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemDriverBinding = DataBindingUtil.inflate(inflater, R.layout.item_driver, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataDriverList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val driver = dataDriverList[position]
        holder.binding.driver = driver
        holder.itemView.setOnClickListener {
            if(driverType == driverAll) {
                val intent = Intent(context, DetailDriverActivity::class.java)
                intent.putExtra("driver", driver)
                context.startActivity(intent)
            }else{
                onClickDriverByLokasiListener.onClicDriverListener(driver)
            }
        }
    }

    fun setData(newData: List<Driver>){
        val diffUtilCustom = DiffUtilCustom(dataDriverList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCustom)
        dataDriverList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}