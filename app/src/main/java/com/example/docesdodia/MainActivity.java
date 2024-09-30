package com.example.docesdodia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText tsenha,tEmail;
    private Button btLogin;
    private ProgressBar progressBar;
    String[] mensagens = {"Preencha todos os campos", "Login Realizado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        IniciarComponentes();

        Button btCadastro = (Button) findViewById(R.id.bt_Cadastro);
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btCadastroActivity();


            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = tEmail.getText().toString();
                String senha = tsenha.getText().toString();

                if (email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else{
                    AutenticarUsuario();
                }
            }
        });

    }
    private  void AutenticarUsuario(){

        String email = tEmail.getText().toString();
        String senha = tsenha.getText().toString();

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    }, 3300);

                    // Obtenha o ID do usuário atual
                    String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    // Consulta para obter o documento do carrinho com base no ID do usuário
                    db.collection("carrinho")
                            .whereEqualTo("userId", userId)
                            .get()
                            .addOnSuccessListener(querySnapshot -> {
                                if (!querySnapshot.isEmpty()) {
                                    // O carrinho do usuário já existe, obtenha o ID do documento
                                    String carrinhoId = querySnapshot.getDocuments().get(0).getId();

                                    // Use o carrinhoId conforme necessário
                                    // Por exemplo, para adicionar o ID do usuário ao carrinho
                                    db.collection("carrinho")
                                            .document(carrinhoId)
                                            .update("Usuarios", userId)
                                            .addOnSuccessListener(aVoid -> {
                                                // O ID do usuário foi adicionado com sucesso ao documento do carrinho
                                            })
                                            .addOnFailureListener(e -> {
                                                // Tratar o erro de adicionar o ID do usuário ao documento do carrinho
                                            });
                                } else {
                                    // O carrinho do usuário ainda não existe, crie um novo carrinho e obtenha o ID do documento
                                    // Crie um novo documento para o carrinho
                                    Map<String, Object> carrinhoData = new HashMap<>();
                                    carrinhoData.put("Usuario", userId);

                                    db.collection("carrinho")
                                            .add(carrinhoData)
                                            .addOnSuccessListener(documentReference -> {
                                                String carrinhoId = documentReference.getId();

                                                // Use o carrinhoId conforme necessário
                                                // Por exemplo, para adicionar o ID do usuário ao carrinho
                                                db.collection("carrinho")
                                                        .document(carrinhoId)
                                                        .update("Usuarios", userId)
                                                        .addOnSuccessListener(aVoid -> {
                                                            // O ID do usuário foi adicionado com sucesso ao documento do carrinho
                                                        })
                                                        .addOnFailureListener(e -> {
                                                            // Tratar o erro de adicionar o ID do usuário ao documento do carrinho
                                                        });
                                            })
                                            .addOnFailureListener(e -> {
                                                // Tratar o erro de criação do carrinho
                                            });
                                }
                            })
                            .addOnFailureListener(e -> {
                                // Tratar o erro de consulta do carrinho
                            });
                }
            }
        });
    }

    private void TelaPrincipal(){
        Intent intent = new Intent(MainActivity.this,Cardapio.class);
        startActivity(intent);
        finish();
    }

    private void IniciarComponentes(){
        tEmail = findViewById(R.id.tEmail);
        tsenha = findViewById(R.id.tsenha);
        btLogin = findViewById(R.id.btLogin);
        progressBar = findViewById(R.id.progressBar);

    }

    private void btCadastroActivity() {

        startActivity(new Intent(MainActivity.this, Cadastro.class));
    }



}