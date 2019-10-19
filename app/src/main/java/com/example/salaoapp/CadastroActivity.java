package com.example.salaoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.Usuario;
import Services.AlertService;
import Services.UserService;


public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastrarUser;
    private Button btnCancelarCadastro;
    private EditText nome;
    private EditText telefone;
    private EditText login;
    private EditText senha;
    private EditText repita_senha;
    private AlertService alert;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        btnCadastrarUser = findViewById(R.id.btnCadastrarUser);
        btnCancelarCadastro =  findViewById(R.id.btnCancelarCadastroUser);
        nome = findViewById(R.id.editTextNome);
        telefone = findViewById(R.id.editTextTelefone);
        login = findViewById(R.id.editTextLoginCadastro);
        senha = findViewById(R.id.editTextSenhaCadastro);
        repita_senha = findViewById(R.id.editTextSenha2Cadastro);



        btnCadastrarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario user = new Usuario();
                user.setNome(nome.getText().toString());
                user.setLogin(login.getText().toString());
                user.setTelefone(telefone.getText().toString());

                //Valida se usuario digitou a mesma senha duas vezes
                if (senha.getText().toString().equals(repita_senha.getText().toString())) {
                    user.setSenha(senha.getText().toString());

                    UserService userService = new UserService();
                    boolean cadastrou = userService.createUser(user);

                    if (cadastrou) {
                        //Cria o gerador do AlertDialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity.this);
                        //define o titulo
                        builder.setTitle("Usuario Cadastrado");
                        //define a mensagem
                        builder.setMessage("Cadastro realizado com sucesso!");
                        //define um botão como positivo
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                                startActivity(it);
                            }
                        });
                        //cria o AlertDialog
                        builder.create();
                        //Exibe
                        builder.show();

                    }

                } else {
                   alert.exibirAlerta("Senhas diferentes", "Senha digitadas diferentes por favor digite novamente", CadastroActivity.this);

                }
            }
        });

        btnCancelarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(CadastroActivity.this,LoginActivity.class);
                startActivity(it);
            }
        });
    }
}
