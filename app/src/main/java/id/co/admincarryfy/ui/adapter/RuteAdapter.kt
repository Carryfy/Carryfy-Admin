package id.co.admincarryfy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.co.admincarryfy.R
import id.co.admincarryfy.data.model.Perjalanan
import id.co.admincarryfy.databinding.ItemRuteBinding

class RuteAdapter(val showDetail : (Perjalanan) -> Unit): RecyclerView.Adapter<RuteAdapter.ViewHolder>() {

    private val listRute = ArrayList<Perjalanan>()

    fun setListRute(listRute: List<Perjalanan>){
        this.listRute.clear()
        this.listRute.addAll(listRute)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RuteAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRuteBinding = DataBindingUtil.inflate(inflater, R.layout.item_rute, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RuteAdapter.ViewHolder, position: Int) {
        val rute = listRute[position]
        holder.bind(rute)
    }

    override fun getItemCount(): Int = listRute.size


    inner class ViewHolder(val binding: ItemRuteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(perjalanan: Perjalanan){
            with(binding){
                tvAsal.text = perjalanan.lokPenjemputan
                tvTujuan.text = perjalanan.lokTujuan
                "${perjalanan.jamBerangkat} ${perjalanan.jamSampai}".also { tvJam.text = it }
                tvHarga.text = perjalanan.biaya
            }

            itemView.setOnClickListener {
                showDetail(perjalanan)
            }

        }
    }


}