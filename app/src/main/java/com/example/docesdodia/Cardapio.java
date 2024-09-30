package com.example.docesdodia;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.docesdodia.Adapter.CardapioAdapter;
import com.example.docesdodia.model.ItemCardapio;


import java.util.ArrayList;
import java.util.List;

public class Cardapio extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CardapioAdapter adapter;

    ImageView imageViewAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        imageViewAdd = findViewById(R.id.ircarrinho);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Cardapio.this,Carrinho.class);
                startActivity(intent);
            }
        });

        imageViewAdd = findViewById(R.id.ircardapio);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Cardapio.this,Cardapio.class);
                startActivity(intent);
            }
        });

        imageViewAdd = findViewById(R.id.iconperfil);
        imageViewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Cardapio.this,TelaPerfil.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ItemCardapio> itensCardapio = criarItensCardapio();
        adapter = new CardapioAdapter(this, itensCardapio);
        recyclerView.setAdapter(adapter);
    }


    private List<ItemCardapio> criarItensCardapio() {
        List<ItemCardapio> itensCardapio = new ArrayList<>();

        ///pra colocar mais itens é só seguir o padrao///
        itensCardapio.add(new ItemCardapio(R.drawable.bolodechocolatevulcao, "Bolo de chocolate", "Bolinho gostoso", "R$ 10,00"));
        itensCardapio.add(new ItemCardapio(R.drawable.bolomandioca, "Nome do Item 2", "Descrição do Item 2", "R$ 15,00"));
        itensCardapio.add(new ItemCardapio(R.drawable.bolocenoura, "Nome do Item 2", "Descrição do Item 2", "R$ 15,00"));
        itensCardapio.add(new ItemCardapio(R.drawable.bolodepote, "Nome do Item 2", "Descrição do Item 2", "R$ 15,00"));



        return itensCardapio;
    }

}
