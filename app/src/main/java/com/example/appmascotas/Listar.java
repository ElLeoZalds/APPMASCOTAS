package com.example.appmascotas;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {
    ListView lstMascotas;
    private final String URL = "http://192.168.1.103:3000/mascotas";
    RequestQueue requestQueue;
    private void loadUI() {
        lstMascotas = findViewById(R.id.lstMascotas);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        loadUI();
        getData();
    }
    private void getData() {
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        Log.d("Datos recibidos", jsonArray.toString());
                        renderData(jsonArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e( "Error WS:", volleyError.toString());
                    }
                }
        );
        //3. Enviar la solicitud
        requestQueue.add(jsonArrayRequest);
    }

    /**
     * Este método renderiza los datos obtenidos por el Web Service en ListView
     */
    private void renderData(JSONArray mascotas) {
        try {
            ArrayAdapter arrayAdapter;
            ArrayList<String> listaMascotas = new ArrayList<>();

            for (int i = 0; i < mascotas.length(); i++) {
                JSONObject jsonObject = mascotas.getJSONObject(i);
                listaMascotas.add(jsonObject.getString("nombre") + " " + jsonObject.getString("tipo") + " " + jsonObject.getString("raza") + " (" + jsonObject.getString("genero") + ")" );
            }
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaMascotas);
            lstMascotas.setAdapter(arrayAdapter);
        } catch (Exception error) {
            Log.e("Error JSON recibido", error.toString());
        }
    }
}