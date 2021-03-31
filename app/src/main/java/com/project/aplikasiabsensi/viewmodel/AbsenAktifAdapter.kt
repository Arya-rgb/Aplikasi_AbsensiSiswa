package com.project.aplikasiabsensi.viewmodel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.model.AbsenResponse
import com.project.aplikasiabsensi.model.PostResponse
import com.project.aplikasiabsensi.view.AbsenSiswaActivity
import com.project.aplikasiabsensi.view.DetailPelajaranActivity

class AbsenAktifAdapter(private val list: ArrayList<AbsenResponse>): RecyclerView.Adapter<AbsenAktifAdapter.AbsenViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbsenAktifAdapter.AbsenViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.absen_item, parent, false)
        return AbsenViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbsenAktifAdapter.AbsenViewHolder, position: Int) {
        holder.bind(list[position])
        val  data = list[position]
        val dataIntent = AbsenResponse(
            data.id_absen,
            data.hari_mapel,
            data.waktu_mapel,
            data.kelas_mapel,
            data.nama_guru,
            data.semester,
            data.pin_absen,
            data.nama_mapel,
        )
        val sData = holder.itemView.context
        holder.itemView.setOnClickListener {
            val moveIntent = Intent(sData, AbsenSiswaActivity::class.java)
            moveIntent.putExtra(AbsenSiswaActivity.EXTRA_DETAIL, dataIntent)
            sData.startActivity(moveIntent)
        }
    }

    override fun getItemCount(): Int = list.size

    inner class AbsenViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: AbsenResponse) {
            with(itemView) {
                findViewById<TextView>(R.id.absn_id).text = post.id_absen
                findViewById<TextView>(R.id.absn_namamapel).text = post.nama_mapel
                findViewById<TextView>(R.id.absn_harimapel).text = post.hari_mapel
                findViewById<TextView>(R.id.absn_jammapel).text = post.waktu_mapel
                findViewById<TextView>(R.id.absn_kelasmapel).text = post.kelas_mapel
                findViewById<TextView>(R.id.absn_namaguru).text = post.nama_guru
                findViewById<TextView>(R.id.absn_semester).text = post.semester
            }

        }
    }


}