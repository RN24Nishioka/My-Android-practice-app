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

public class NumAdapter extends RecyclerView.Adapter<NumAdapter.NumViewHolder> implements View.OnClickListener{

    int number = 1;



    Activity mActivity;
    String[] mNum;

    public  NumAdapter(Activity activity, String[] mNum){
        this.mActivity = activity;
        this.mNum = mNum;
    }

    @Override
    public void onClick(View v) {

        Button addButton = mActivity.findViewById(R.id.button);
        Button minusButton = mActivity.findViewById(R.id.button2);

        TextView txtNum = v.findViewById(R.id.txt_num);
        Context context = v.getContext();
        String currentNum = txtNum.getText().toString();
        Toast.makeText(context, currentNum, Toast.LENGTH_SHORT).show();

        number = Integer.parseInt(currentNum);

        addButton.setText("+" + number);
        minusButton.setText("-" + number);

    }

    @NonNull
    @Override
    public NumAdapter.NumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View numView = inflater.inflate(R.layout.num_item, parent, false);

        numView.setOnClickListener(this);

        NumViewHolder viewHolder = new NumViewHolder(numView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumAdapter.NumViewHolder holder, int position) {

        String currentNumbers = mNum[position];
        holder.txtNum.setText(currentNumbers);

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
