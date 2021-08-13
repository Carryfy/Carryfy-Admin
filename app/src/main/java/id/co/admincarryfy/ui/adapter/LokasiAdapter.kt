package id.co.admincarryfy.ui.adapter

import android.location.Address
import android.location.Geocoder
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import id.co.admincarryfy.data.model.Lokasi
import id.co.admincarryfy.databinding.ItemLokasiBinding
import java.io.IOException

class LokasiAdapter(val showMaps: (String, String) -> Unit) : RecyclerView.Adapter<LokasiAdapter.ViewHolder>(){

    val listLokasi = ArrayList<Lokasi>()

    fun setLokasiList(listLokasi: List<Lokasi>){
        this.listLokasi.clear()
        this.listLokasi.addAll(listLokasi)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemLokasiBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(lokasi: Lokasi){
            with(binding){
                val geocoder = Geocoder(itemView.context)
                val addresses: List<Address>
                try {
                    val koordinat = lokasi.koordinat!!
                        .replace("{", "")
                        .replace("}","")

                    val latlngKoordinat = koordinat.split(", ")
                    addresses = geocoder.getFromLocation(latlngKoordinat[0].toDouble(), latlngKoordinat[1].toDouble(), 5)
                    if (addresses.size > 0) {
                        val locationName = addresses[0].getAddressLine(0)
                        tvLokasi.text = locationName.toString()
                    }else{
                        tvLokasi.text = ""
                    }


                    tvDate.text = DateFormat.format("HH:mm dd-MM-yyyy", lokasi.tglMasuk).toString()

                    tvMaps.setOnClickListener {
                        showMaps(latlngKoordinat[0], latlngKoordinat[1])
                    }

                } catch (e: IOException) {
                    Log.d("ERROR ROUTE", "displayLocation: $e")
                }


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemLokasiBinding = DataBindingUtil.inflate(inflater, id.co.admincarryfy.R.layout.item_lokasi, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val lokasi = listLokasi[position]
        holder.bind(lokasi)
    }

    override fun getItemCount(): Int = listLokasi.size

}