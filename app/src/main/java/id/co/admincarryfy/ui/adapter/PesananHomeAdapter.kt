package id.co.admincarryfy.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Pesanan
import id.co.admincarryfy.databinding.ItemPesananBinding
import id.co.admincarryfy.ui.pesanan.DetailPerjalananActivity
import id.co.admincarryfy.util.DiffUtilCustom

class PesananHomeAdapter(val context: Context): RecyclerView.Adapter<PesananHomeAdapter.ViewHolder>() {

    private var dataPesananList = emptyList<Pesanan>()
    

    inner class ViewHolder(val dataBinding: ItemPesananBinding): RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding: ItemPesananBinding = DataBindingUtil.inflate(inflater, R.layout.item_pesanan, parent, false)
        return ViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = dataPesananList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding.pesanan = dataPesananList[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailPerjalananActivity::class.java)
            intent.putExtra("pesanan", dataPesananList[position])
            context.startActivity(intent)
        }

    }

    fun setData(newData: List<Pesanan>){
        val diffUtilCustom = DiffUtilCustom(dataPesananList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtilCustom)
        dataPesananList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }

}