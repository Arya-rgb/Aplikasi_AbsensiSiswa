package com.project.aplikasiabsensi.model;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "https://alihyaabsendata.000webhostapp.com/deleteaktif.php";
    private final Map<String, String> params;

    public DeleteRequest(String id_absen, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("id_absen", id_absen);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
