package com.project.aplikasiabsensi.viewmodel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.model.PostResponse
import com.project.aplikasiabsensi.view.DetailPelajaranActivity

class PelajaranAdapter(private val list: ArrayList<PostResponse>): RecyclerView.Adapter<PelajaranAdapter.PostViewHolder>() {



    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostResponse) {
            with(itemView) {
                findViewById<TextView>(R.id.id).text = post.id_mapel
                findViewById<TextView>(R.id.absn_namamapel).text = post.nama_mapel
                findViewById<TextView>(R.id.absn_harimapel).text = post.hari_mapel
                findViewById<TextView>(R.id.jamMapel).text = post.jam_mapel
                findViewById<TextView>(R.id.absn_kelasmapel).text = post.kelas_mapel
                findViewById<TextView>(R.id.absn_namaguru).text = post.nama_guru
                findViewById<TextView>(R.id.semester).text = post.semester
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pelajaran_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
       holder.bind(list[position])
        val  data = list[position]
        val dataIntent = PostResponse(
            data.id_mapel,
            data.nama_mapel,
            data.hari_mapel,
            data.jam_mapel,
            data.kelas_mapel,
            data.nama_guru,
            data.semester
        )
        val sData = holder.itemView.context
        holder.itemView.setOnClickListener {
            val moveIntent = Intent(sData, DetailPelajaranActivity::class.java)
            moveIntent.putExtra(DetailPelajaranActivity.EXTRA_DETAIL, dataIntent)
            sData.startActivity(moveIntent)
        }
    }

    override fun getItemCount(): Int = list.size

}