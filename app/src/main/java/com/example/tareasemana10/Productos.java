package com.example.tareasemana10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class Productos extends AppCompatActivity {
public EditText nombre;
public EditText producto;
public EditText cantidad;
public ImageButton enviar;
public ImageButton ver;
public ImageButton limp;
public String cliente;
public String product;
public String cant;
RequestQueue requestQueue;
ProgressDialog progressDialog;
    String HttpUrl = "https://rightward-column.000webhostapp.com/laapp.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        nombre = findViewById(R.id.edtnombre);
        producto = findViewById(R.id.edtproducto);
        cantidad = findViewById(R.id.edtcantidad);
        enviar = findViewById(R.id.imagepedido);
        ver = findViewById(R.id.imagever);
        limp = findViewById(R.id.imagelimp);
        requestQueue = Volley.newRequestQueue(Productos.this);
        progressDialog = new ProgressDialog(Productos.this);

        limp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    nombre.setText("");
                    producto.setText("");
                    cantidad.setText("");
                    Toast.makeText(Productos.this, "Se ha limpiado correctamente", Toast.LENGTH_SHORT).show();
            }
        });
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Productos.this,Login.class);
                startActivity(intent);
            }
        });
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Espera, Se esta insertando los datos en la base de datos");
                progressDialog.show();
                valoresedittext();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, HttpUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String ServerResponse) {
                                progressDialog.dismiss();
                                Toast.makeText(Productos.this, "Se han enviado los datos con exito", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                    private VolleyError error;
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                progressDialog.dismiss();
                                Toast.makeText(Productos.this, "Ha fallado el envio", Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("nombre",cliente);
                        params.put("nombreproducto",product);
                        params.put("cantidad",cant);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(Productos.this);
                requestQueue.add(stringRequest);
                nombre.setText("");
                producto.setText("");
                cantidad.setText("");
            }
        });
    }

    public void valoresedittext(){
        cliente = nombre.getText().toString().trim();
        product = producto.getText().toString().trim();
        cant = cantidad.getText().toString().trim();
    }

}