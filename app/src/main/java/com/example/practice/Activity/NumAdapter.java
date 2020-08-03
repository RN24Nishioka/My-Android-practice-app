package com.example.practice.Activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.NumViewHolder>{

    Listener listener;

    int number = 1;


    String[] mNum;

    interface Listener{
        void onClick(int number);
    }

    public  NumAdapter(String[] mNum,Listener listener){
        this.mNum = mNum;
        this.listener = listener;

    }

    @NonNull
    @Override
    public NumAdapter.NumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View numView = inflater.inflate(R.layout.num_item, parent, false);
        NumViewHolder viewHolder = new NumViewHolder(numView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumAdapter.NumViewHolder holder, final int position) {

        String currentNumbers = mNum[position];
        holder.txtNum.setText(currentNumbers);
        holder.txtNum.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                listener.onClick(Integer.parseInt(mNum[position]));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNum.length;
    }

    public class NumViewHolder extends RecyclerView.ViewHolder{
        TextView txtNum;

        public NumViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNum = itemView.findViewById(R.id.txt_num);
        }



    }
}
