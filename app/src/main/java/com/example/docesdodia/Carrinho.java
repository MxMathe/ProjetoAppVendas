package com.example.docesdodia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docesdodia.Adapter.CarrinhoAdapter;
import com.example.docesdodia.model.ItemCarrinho;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class Carrinho extends AppCompatActivity {

    private RecyclerView recyclerViewCarrinho;
    private CarrinhoAdapter adapter;
    private List<ItemCarrinho> itensCarrinho;

    ImageView imageViewAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);


        imageViewAdd = findViewById(R.id.ircardapio);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Carrinho.this,Cardapio.class);
                startActivity(intent);
            }
        });

        imageViewAdd = findViewById(R.id.iconperfil);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Carrinho.this,TelaPerfil.class);
                startActivity(intent);
            }
        });

        recyclerViewCarrinho = findViewById(R.id.recyclerViewCarrinho);
        recyclerViewCarrinho.setLayoutManager(new LinearLayoutManager(this));

        itensCarrinho = new ArrayList<>();
        adapter = new CarrinhoAdapter(itensCarrinho);
        recyclerViewCarrinho.setAdapter(adapter);

        recuperarItensDoCarrinho();
    }

    private void recuperarItensDoCarrinho() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("carrinho")
                .document(userId)
                .collection("itens")
                .addSnapshotListener((value, error) -> {
                    if (error != null) {
                        // Tratar o erro de recuperação dos itens do carrinho
                        return;
                    }

                    itensCarrinho.clear();
                    for (DocumentSnapshot document : value.getDocuments()) {
                        String nome = document.getString("nome");
                        String descricao = document.getString("descricao");
                        String valor = document.getString("valor");
                        ItemCarrinho item = new ItemCarrinho(nome, descricao, valor);
                        itensCarrinho.add(item);
                    }
                    adapter.notifyDataSetChanged();
                });
    }
    }
