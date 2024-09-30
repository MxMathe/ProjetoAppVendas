package com.example.docesdodia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NovoPedido extends AppCompatActivity {

    private EditText tNomeCliente,tData,tEndereco,tDescri;
    private ProgressBar progressBar;
    String[] mensagens = {"Pedido Salvo", "teste"};
    String pedidoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_pedido);

        getSupportActionBar().hide();
        IniciarComponentes();

        Button btSalvar = (Button) findViewById(R.id.btSalvar);
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btSalvarActivity();

            }
        });

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeCliente = tNomeCliente.getText().toString();
                String data = tData.getText().toString();
                String endereco = tEndereco.getText().toString();
                String descricao = tDescri.getText().toString();

                if (nomeCliente.isEmpty() || data.isEmpty() || endereco.isEmpty() || descricao.isEmpty()){
                    Snackbar snackbar = Snackbar.make(view,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.BLUE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();

                    progressBar.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    },3300);

                }else {
                    SalvarDadosCliente();
                }
            }
        });
    }

    private void TelaPrincipal(){
        Intent intent = new Intent(NovoPedido.this,TelaPerfil.class);
        startActivity(intent);
        finish();
    }

    private void SalvarDadosCliente(){

        String nomeCliente = tNomeCliente.getText().toString();
        String data = tData.getText().toString();
        String endereco = tEndereco.getText().toString();
        String descricao = tDescri.getText().toString();


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String,Object> Pedido = new HashMap<>();
        Pedido.put("nomeCliente",nomeCliente);
        Pedido.put("data",data);
        Pedido.put("endereco",endereco);
        Pedido.put("descricao",descricao);

        pedidoID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Pedidos").document(pedidoID);
        documentReference.set(Pedido).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d("db","Sucesso");

            }
        })
         .addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 Log.d("db_error", "Erro ao Salvar" + e.toString());
             }
         });
    }

    private void  IniciarComponentes(){

        tNomeCliente = findViewById(R.id.tNomeCliente);
        tData = findViewById(R.id.tData);
        tEndereco = findViewById(R.id.tEndereco);
        tDescri = findViewById(R.id.tDescri);
    }

    private void btSalvarActivity(){
        startActivity(new Intent(NovoPedido.this, TelaPerfil.class));
    }

}