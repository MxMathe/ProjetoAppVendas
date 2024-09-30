package com.example.docesdodia.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.docesdodia.R;
import com.example.docesdodia.model.ItemCarrinho;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.ViewHolder> {

    private List<ItemCarrinho> itensCarrinho;

    public CarrinhoAdapter(List<ItemCarrinho> itensCarrinho) {
        this.itensCarrinho = itensCarrinho;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ItemCarrinho item = itensCarrinho.get(position);
        holder.nomeTextView.setText(item.getNome());
        holder.descricaoTextView.setText(item.getDescricao());
        holder.valorTextView.setText(item.getValor());
    }


    @Override
    public int getItemCount() {
        return itensCarrinho.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeTextView;
        public TextView descricaoTextView;
        public TextView valorTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.nomeTextView);
            descricaoTextView = itemView.findViewById(R.id.descricaoTextView);
            valorTextView = itemView.findViewById(R.id.valorTextView);
        }
    }
}