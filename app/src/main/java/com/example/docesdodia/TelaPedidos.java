package com.example.docesdodia;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.docesdodia.Adapter.PedidoAdapter;
import com.example.docesdodia.NovoPedido;
import com.example.docesdodia.R;
import com.example.docesdodia.TelaPerfil;
import com.example.docesdodia.model.Pedido;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class TelaPedidos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PedidoAdapter pedidoAdapter;
    private List<Pedido> listaPedidos;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView imageViewAdd, imageViewPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_pedidos);

        recyclerView = findViewById(R.id.recyclerPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listaPedidos = new ArrayList<>();
        pedidoAdapter = new PedidoAdapter(listaPedidos);
        recyclerView.setAdapter(pedidoAdapter);

        imageViewAdd = findViewById(R.id.imageViewAdd);
        imageViewAdd.setOnClickListener(view -> {
            Intent intent = new Intent(TelaPedidos.this, NovoPedido.class);
            startActivity(intent);
        });

        imageViewPerfil = findViewById(R.id.imageViewPerfil);
        imageViewPerfil.setOnClickListener(view -> {
            Intent intent = new Intent(TelaPedidos.this, TelaPerfil.class);
            startActivity(intent);
        });

        carregarPedidos();
    }

    private void carregarPedidos() {
        String usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db.collection("Pedidos")
                .whereEqualTo("userID", usuarioID)  // Certifique-se de que cada pedido tem um campo "userID"
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {
                        if (queryDocumentSnapshots != null) {
                            listaPedidos.clear();
                            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                Pedido pedido = document.toObject(Pedido.class);
                                listaPedidos.add(pedido);
                            }
                            pedidoAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
