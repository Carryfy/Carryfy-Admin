package id.co.admincarryfy.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Pesanan (
    @SerializedName("id_pesanan")
    var idPesanan: String ?= "",

    @SerializedName("no_hp_user")
    var noHpUser: String ?= "",

    @SerializedName("lok_penjemputan")
    var lokPenjemputan: String ?= "",

    @SerializedName("lok_tujuan")
    var lokTujuan: String ?= "",

    @SerializedName("penumpang_dewasa")
    var penumpangDewasa: String ?= "",

    @SerializedName("penumpang_anak")
    var penumpangAnak: String ?= "",

    @SerializedName("barang_bawaan")
    var barangBawaan: String ?= "",

    @SerializedName("biaya")
    var biaya: String ?= "",

    @SerializedName("no_hp_utama")
    var noHpUtama: String ?= "",

    @SerializedName("tgl_pesanan")
    var tglPesanan: Date ?= Date(),

    @SerializedName("status_pesanan")
    var statusPesanan: String ?= "",

    @SerializedName("keberangakatan")
    var keberangkatan: String ?= ""
)