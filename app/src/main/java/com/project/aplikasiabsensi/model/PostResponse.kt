package com.project.aplikasiabsensi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponse (
    var id_mapel: String? = "",
    var nama_mapel: String? = "",
    var hari_mapel: String? = "",
    var jam_mapel: String? = "",
    var kelas_mapel: String? = "",
    var nama_guru: String? = "",
    var semester: String? = "",
) : Parcelable