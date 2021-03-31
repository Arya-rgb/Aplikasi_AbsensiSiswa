package com.project.aplikasiabsensi.model;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AbsenSiswaRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://alihyaabsendata.000webhostapp.com/absensi.php";
    private final Map<String, String> params;

    public AbsenSiswaRequest(String id_pelajar, String nama_pelajar, String kelas_pelajar, String nama_mapel, String jam_masuk, Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_pelajar", id_pelajar);
        params.put("nama_pelajar", nama_pelajar);
        params.put("kelas_pelajar", kelas_pelajar);
        params.put("nama_mapel", nama_mapel);
        params.put("jam_masuk", jam_masuk);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}