package com.example.appmascotas;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Registrar extends AppCompatActivity {
    EditText edtNombre, edtTipo, edtRaza, edtColor, edtPeso, edtGenero;
    Button btnGuardar;
    private final String URL = "http://192.168.1.103:3000/mascotas";
    RequestQueue requestQueue;
    private void loadUI() {
        edtNombre = findViewById(R.id.edtNombre);
        edtTipo = findViewById(R.id.edtTipo);
        edtRaza = findViewById(R.id.edtRaza);
        edtColor = findViewById(R.id.edtColor);
        edtPeso = findViewById(R.id.edtPeso);
        edtGenero = findViewById(R.id.edtGenero);

        btnGuardar = findViewById(R.id.btnGuardar);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataWS();
            }
        });
    }
    private void sendDataWS() {
        requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonObject = new JSONObject();
        try {
            //El JSON recibe los datos de las cajas (EditText)
            jsonObject.put("nombre", edtNombre.getText().toString());
            jsonObject.put("tipo", edtTipo.getText().toString());
            jsonObject.put("raza", edtRaza.getText().toString());
            jsonObject.put("color", edtColor.getText().toString());
            jsonObject.put("peso", Double.parseDouble(edtPeso.getText().toString()));
            jsonObject.put("genero", edtGenero.getText().toString());
        } catch (Exception error) {
            Log.e("Error JSON envío", error.toString());
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.d("Id obtenido", jsonObject.toString());
                        Toast.makeText(Registrar.this, "Mascota registrada correctamente", Toast.LENGTH_SHORT).show();                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Registrar.this, "Error al enviar: ", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}