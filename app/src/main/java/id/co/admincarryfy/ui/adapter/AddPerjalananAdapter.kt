package id.co.admincarryfy.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.admincarryfy.R
import id.co.admincarryfy.data.database.entities.PerjalananEntities
import id.co.admincarryfy.data.model.Driver
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ItemTambahPerjalananBinding
import id.co.admincarryfy.util.DiffUtilCustom

class AddPerjalananAdapter(val context: Context): RecyclerView.Adapter<AddPerjalananAdapter.ViewHolder>() {

    var dataPerjalananList = emptyList<Perjalanan>()

    inner class ViewHolder(val binding: ItemTambahPerjalananBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemTambahPerjalananBinding = DataBindingUtil.inflate(inflater, R.layout.item_tambah_perjalanan, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataPerjalananList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val perjalanan = dataPerjalananList[position]
        val perjalananDriver = perjalanan?.lokTujuan+" - "+perjalanan?.lokPenjemputan
        val jamDriver = perjalanan?.jamBerangkat+" - "+perjalanan?.jamSampai
        holder.binding.tvPerjalanan.text = perjalananDriver
        holder.binding.tvJam.text = jamDriver
        holder.binding.tvBiaya.text = perjalanan?.biaya
        holder.binding.tvHari.text = perjalanan?.hariKeberangkatan
    }

    fun setData(newData: List<Perjalanan>){
        val diffUtilCustom = DiffUtilCustom(dataPerjalananList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCustom)
        dataPerjalananList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}