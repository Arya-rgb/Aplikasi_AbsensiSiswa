package com.project.aplikasiabsensi.model;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AbsensiRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://alihyaabsendata.000webhostapp.com/aktivasiabsen.php";
    private final Map<String, String> params;

    public AbsensiRequest(String id_absen, String hari_mapel, String waktu_mapel, String kelas_mapel, String nama_guru,
                          String semester, String pin_absen, String nama_mapel, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_absen", id_absen);
        params.put("hari_mapel", hari_mapel);
        params.put("waktu_mapel", waktu_mapel);
        params.put("kelas_mapel", kelas_mapel);
        params.put("nama_guru", nama_guru);
        params.put("semester", semester);
        params.put("pin_absen", pin_absen);
        params.put("nama_mapel", nama_mapel);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}