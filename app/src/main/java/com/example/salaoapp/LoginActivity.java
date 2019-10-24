package com.example.salaoapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import Model.Usuario;
import Services.UserService;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    Button btn_cadastro;
    EditText login;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_cadastro = (Button) findViewById(R.id.btnCadastro);
        login = findViewById(R.id.editTextLogin);
        senha = findViewById(R.id.editTextSenhaLogin);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = new Usuario();
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());

                UserService usuarioWS = new UserService();
                usuarioWS.singInUser(usuario);
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
