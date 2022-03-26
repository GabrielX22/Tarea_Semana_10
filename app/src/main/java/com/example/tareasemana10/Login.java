package com.example.tareasemana10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends AppCompatActivity {
public EditText usuario;
public EditText contrasenia;
public ImageButton verpedido;
public Button regresar;
public String usu;
public String contra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuario = findViewById(R.id.edtusuario);
        contrasenia = findViewById(R.id.edtcontra);
        verpedido = findViewById(R.id.imageverdos);
        regresar = findViewById(R.id.btnregresar);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        verpedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usu = usuario.getText().toString();
                contra = contrasenia.getText().toString();
                if(usu.equals("Admin1") && contra.equals("eladmin22")){
                    String uri="https://rightward-column.000webhostapp.com/sistema/index.php";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(uri)));
                }
                else{
                    Toast.makeText(Login.this, "Ingrese los datos correctos", Toast.LENGTH_SHORT).show();
                    Vibrator variable = (Vibrator)
                            getSystemService(Context.VIBRATOR_SERVICE);
                    variable.vibrate(500);
                }
            }
        });
    }
}