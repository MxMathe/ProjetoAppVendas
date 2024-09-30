package com.example.docesdodia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.docesdodia.R;
import com.example.docesdodia.model.ItemCardapio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardapioAdapter extends RecyclerView.Adapter<CardapioAdapter.ViewHolder> {

    private Context context;
    private List<ItemCardapio> itensCardapio;

    public CardapioAdapter(Context context, List<ItemCardapio> itensCardapio){
        this.context = context;
        this.itensCardapio = itensCardapio;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cardapioView = inflater.inflate(R.layout.item_cardapio, parent, false);
        return new ViewHolder(cardapioView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ItemCardapio item = itensCardapio.get(position);
        holder.selecionarButton.setTag(item);
        holder.imageView.setImageResource(item.getImagem());
        holder.nomeTextView.setText(item.getNome());
        holder.descricaoTextView.setText(item.getDescricao());
        holder.valorTextView.setText(item.getValor());

        final int itemPosition = position;
        holder.selecionarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemCardapio selectedItem = (ItemCardapio) v.getTag();
                enviarItemParaFirebase(selectedItem);
            }
        });

    }
    private void enviarItemParaFirebase(ItemCardapio item) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Criar um novo documento com um ID gerado automaticamente
        DocumentReference docRef = db.collection("carrinho").document(userId).collection("itens").document();
        String itemId = docRef.getId(); // Obter o ID gerado

        // Criar um mapa com os dados do item
        Map<String, Object> itemData = new HashMap<>();
        itemData.put("nome", item.getNome());
        itemData.put("descricao", item.getDescricao());
        itemData.put("valor", item.getValor());

        // Salvar os dados do item no Firestore
        docRef.set(itemData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Adicionado ao carrinho", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Erro ao selecionar o item", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    @Override
    public int getItemCount() {
        return itensCardapio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView nomeTextView;
        public TextView descricaoTextView;
        public TextView valorTextView;
        public Button selecionarButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
            valorTextView = itemView.findViewById(R.id.valorTextView);
            selecionarButton = itemView.findViewById(R.id.selecionarButton);
        }
    }
}