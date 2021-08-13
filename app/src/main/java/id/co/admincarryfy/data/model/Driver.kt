package id.co.admincarryfy.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Driver (
    @SerializedName("id_driver_travel")
    var idDriverTravel: String ?= "",
    @SerializedName("nama_driver")
    var namaDriver: String ?= "",
    @SerializedName("no_hp_utama")
    var noHpUtama: String ?= "",
    @SerializedName("no_hp_cadangan")
    var noHpCadangan: String ?= "",
    @SerializedName("jenis_kelamin")
    var jenisKelamin: String ?= "",
    @SerializedName("no_ktp_sim")
    var noKtpSim: String ?= "",
    @SerializedName("merk_kendaraan")
    var merkKendaraan: String ?= "",
    @SerializedName("warna_kendaraan")
    var warnaKendaraan: String ?= "",
    @SerializedName("plat_kendaraan")
    var platKendaraan: String ?= "",
    @SerializedName("lokasi_terkini")
    var lokasiTerkini: String ?= "",
    @SerializedName("kursi_kosong")
    var kursiKosong: String ?= "",
    @SerializedName("token_firebase")
    var tokenFirebase: String ?= "",
    @SerializedName("deposit")
    var deposit: String ?= ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idDriverTravel)
        parcel.writeString(namaDriver)
        parcel.writeString(noHpUtama)
        parcel.writeString(noHpCadangan)
        parcel.writeString(jenisKelamin)
        parcel.writeString(noKtpSim)
        parcel.writeString(merkKendaraan)
        parcel.writeString(warnaKendaraan)
        parcel.writeString(platKendaraan)
        parcel.writeString(lokasiTerkini)
        parcel.writeString(kursiKosong)
        parcel.writeString(tokenFirebase)
        parcel.writeString(deposit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Driver> {
        override fun createFromParcel(parcel: Parcel): Driver {
            return Driver(parcel)
        }

        override fun newArray(size: Int): Array<Driver?> {
            return arrayOfNulls(size)
        }
    }
}