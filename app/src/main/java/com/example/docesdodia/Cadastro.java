package com.example.docesdodia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    private EditText tNomeCad,tEmail,tTelefone,tSenhaCad,tConfirme;
    private Button btCadastrar;
String[] mensagens = {"Preencha todos os campos", "Cadastro Realizado"};
String usuarioID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = tNomeCad.getText().toString();
                String email = tEmail.getText().toString();
                String telefone = tTelefone.getText().toString();
                String senha = tSenhaCad.getText().toString();
                String senhaconfir = tConfirme.getText().toString();

                if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty() || senhaconfir.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    CadastrarUsuario(v);

                }
            }
        });
    }

    private void CadastrarUsuario(View v){

        String email = tEmail.getText().toString();
        String senha = tSenhaCad.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    SalvarDadosUsuario();
                    Snackbar snackbar = Snackbar.make(v,mensagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }else{
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Digite uma senha de no mínimo 6 caracteres";
                    }catch (FirebaseAuthUserCollisionException e) {
                        erro = "Email já cadastrado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro = "Email Invalido";
                    }catch (Exception e){
                        erro = "Erro ao cadastrar";

                    }

                    Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                }

            }
        });

    }

    private void SalvarDadosUsuario(){
        String nome = tNomeCad.getText().toString();
        String telefone = tTelefone.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome",nome);
        usuarios.put("telefone",telefone);

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("db","Cadastro realizado com sucesso");

            }
        })
         .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.d("db_error", "Erro ao salvar os dados" + e.toString());
             }
         });


    }
        private void IniciarComponentes(){

        tNomeCad = findViewById(R.id.tNomeCad);
        tEmail = findViewById(R.id.tEmail);
        tTelefone = findViewById(R.id.tTelefone);
        tSenhaCad = findViewById(R.id.tSenhaCad);
        tConfirme = findViewById(R.id.tConfirme);
        btCadastrar = findViewById(R.id.btCadastrar);
    }
}