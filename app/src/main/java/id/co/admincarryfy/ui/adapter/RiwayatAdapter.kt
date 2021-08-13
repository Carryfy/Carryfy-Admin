package id.co.admincarryfy.ui.adapter

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Riwayat
import id.co.admincarryfy.databinding.ItemRiwayatBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RiwayatAdapter(val showDetail : (Riwayat) -> Unit): RecyclerView.Adapter<RiwayatAdapter.ViewHolder>() {

    val listRiwayat = ArrayList<Riwayat>()

    fun setListRiwayat(listRiwayat: List<Riwayat>){
        this.listRiwayat.clear()
        this.listRiwayat.addAll(listRiwayat)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRiwayatBinding = DataBindingUtil.inflate(inflater, R.layout.item_riwayat, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RiwayatAdapter.ViewHolder, position: Int) {
        val riwayat = listRiwayat[position]
        holder.bind(riwayat)
    }

    override fun getItemCount(): Int = listRiwayat.size


    inner class ViewHolder(val binding: ItemRiwayatBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(riwayat: Riwayat){
            with(binding){
                tvUser.text = riwayat.user.nama_user
                tvAsal.text = riwayat.lok_penjemputan
                tvTujuan.text = riwayat.lok_tujuan

                try{
                    val SDFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                    val cal = Calendar.getInstance()
                    cal.time = SDFormat.parse(riwayat.tgl_berangkat)

                    tvDate.text = DateFormat.format("dd/MM/yyyy HH:mm", cal).toString()
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }

            itemView.setOnClickListener {
                showDetail(riwayat)
            }
        }
    }


}