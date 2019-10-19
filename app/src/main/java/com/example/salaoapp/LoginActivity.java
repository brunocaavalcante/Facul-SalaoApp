package com.example.salaoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import Model.Usuario;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_cadastro = (Button) findViewById(R.id.btnCadastro);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Usuario usuario = new Usuario();
                usuario.setLogin();
                Intent it = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(it);
            }
        });

        btn_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this,CadastroActivity.class);
                startActivity(it);
            }
        });
    }
}
