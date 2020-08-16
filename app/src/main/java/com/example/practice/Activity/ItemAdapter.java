package com.example.practice.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;

import java.util.List;

class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{


    private List<DbTable> shoppingItems;

    public ItemAdapter(List<DbTable> items) {
        this.shoppingItems = items;
    }

    public void setDate(List<DbTable> items) {
        this.shoppingItems = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        DbTable dbTable= shoppingItems.get(position);

        holder.txtItemName.setText(dbTable.item);
        holder.txtDate.setText("期日: "+ dbTable.date);

    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtItemName;
        TextView txtDate;
        ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItemName = itemView.findViewById(R.id.txt_todo);
            txtDate = itemView.findViewById(R.id.textDate);
            imageView = itemView.findViewById(R.id.img_delete);

            imageView.setOnClickListener(this::onClick);
            //txtDate.setText();
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DbTable dbTable = shoppingItems.get(position);
            ItemRepository itemRepository = new ItemRepository(v.getContext());
            itemRepository.deleteItem(dbTable);
            Toast.makeText(v.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();

        }
    }
}
