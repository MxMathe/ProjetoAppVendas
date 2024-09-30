package com.example.docesdodia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaPedidos extends AppCompatActivity {

    private TextView textNomeP,textDescriP,textEnderecoP,textDataP;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;
    ImageView imageViewAdd,imageViewPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedidos);

        IniciarComponentes();

        imageViewAdd = findViewById(R.id.imageViewAdd);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TelaPedidos.this,NovoPedido.class);
                startActivity(intent);
            }
        });

        imageViewPerfil = findViewById(R.id.imageViewPerfil);
        imageViewPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaPedidos.this,TelaPerfil.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Pedidos").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot !=null){
                    textNomeP.setText(documentSnapshot.getString("nomeCliente"));
                    textDescriP.setText(documentSnapshot.getString("descricao"));
                    textEnderecoP.setText(documentSnapshot.getString("endereco"));
                    textDataP.setText(documentSnapshot.getString("data"));


                }
            }
        });
    }

    private void IniciarComponentes(){
        textNomeP = findViewById(R.id.textNomeP);
        textDescriP = findViewById(R.id.textDescriP);
        textEnderecoP = findViewById(R.id.textEnderecoP);
        textDataP = findViewById(R.id.textDataP);
    }

}