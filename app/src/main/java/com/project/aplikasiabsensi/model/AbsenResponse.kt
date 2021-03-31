package com.project.aplikasiabsensi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class AbsenResponse (
    var id_absen: String? = "",
    var hari_mapel: String? = "",
    var waktu_mapel: String? = "",
    var kelas_mapel: String? = "",
    var nama_guru: String? = "",
    var semester: String? = "",
    var pin_absen: String? = "",
    var nama_mapel: String? = "",
): Parcelable