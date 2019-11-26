package com.example.salaoapp;

import Services.AgendaService;
import Services.AlertService;
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
    AlertService alertService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btnLogin);
        btn_cadastro = (Button) findViewById(R.id.btnCadastro);
        login = findViewById(R.id.editTextLogin);
        senha = findViewById(R.id.editTextSenhaLogin);
        alertService = new AlertService();

        UserService userService = new UserService();
        if(userService.verifyAuthentication()){//Verifica se o usuario já está autenticado
            Intent it = new Intent(LoginActivity.this,MainActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //Faz com que a activity que foi chamada seja a principal desta forma o user não podera retornar para tela de login
            startActivity(it);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Usuario usuario = new Usuario();
                usuario.setLogin(login.getText().toString());
                usuario.setSenha(senha.getText().toString());

                if(!usuario.getLogin().isEmpty()  || !usuario.getSenha().isEmpty())
                {
                    UserService usuarioWS = new UserService();
                    if(usuarioWS.singInUser(usuario)){
                        Intent it = new Intent(LoginActivity.this,MainActivity.class);
                        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK); //Faz com que a activity que foi chamada seja a principal desta forma o user não podera retornar para tela de login
                        startActivity(it);
                    }else{
                        alertService.exibirAlerta("Login Inválido!","Usuario ou senha estão incorretos!",LoginActivity.this);
                    }

                }else {
                    alertService.exibirAlerta("Login ou Senha não preenchidos!","Os campos login e senha não podem ficar em branco.",LoginActivity.this);
                }
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
