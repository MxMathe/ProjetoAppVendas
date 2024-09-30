package com.example.docesdodia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaPerfil extends AppCompatActivity {

    private TextView nomeUsuario, emailUsuario;
    private Button bt_deslogar;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;
    ImageView imageViewAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil);

        getSupportActionBar().hide();
        IniciarComponesntes();
        

            imageViewAdd = findViewById(R.id.imagecarrinho);
            imageViewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(TelaPerfil.this,Carrinho.class);
                    startActivity(intent);
                }
            });

            imageViewAdd = findViewById(R.id.imagecarpio);
            imageViewAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(TelaPerfil.this,Cardapio.class);
                    startActivity(intent);
                }
            });


        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(TelaPerfil.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString("nome"));
                    emailUsuario.setText(email);
                }
            }
        });
    }

    private void IniciarComponesntes(){
        nomeUsuario = findViewById(R.id.textNomeUsuario);
        emailUsuario = findViewById(R.id.textemail);
        bt_deslogar = findViewById(R.id.btdeslogar);

    }
}